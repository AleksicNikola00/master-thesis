import { useNavigate } from "react-router-dom";
import ArrowRightIcon from "@mui/icons-material/ArrowRight";
import ArrowLeftIcon from "@mui/icons-material/ArrowLeft";
import { useEffect, useRef, useState } from "react";

export type CarousellPlayer = {
  id: number;
  value: any;
  fullName: string;
  imageUrl: string;
};

type PlayerCarousellProps = {
  title: string;
  label: string;
  players: CarousellPlayer[];
};

const PlayerCarousell = ({ title, players, label }: PlayerCarousellProps) => {
  const navigate = useNavigate();
  const carouselRef = useRef<HTMLUListElement>(null);
  const [canScrollLeft, setCanScrollLeft] = useState(false);
  const [canScrollRight, setCanScrollRight] = useState(false);

  const updateScrollButtons = () => {
    if (!carouselRef.current) return;

    const { scrollLeft, scrollWidth, clientWidth } = carouselRef.current;
    setCanScrollLeft(scrollLeft > 0);
    setCanScrollRight(scrollLeft + 5 < scrollWidth - clientWidth);
  };

  useEffect(() => {
    const carousel = carouselRef.current;
    if (carousel) {
      carousel.addEventListener("scroll", updateScrollButtons);
      updateScrollButtons();
    }

    return () => {
      if (carousel) {
        carousel.removeEventListener("scroll", updateScrollButtons);
      }
    };
  }, []);

  const onPlayerClick = (playerId: number) => {
    navigate(`/player/${playerId}`);
  };

  const scrollCarousel = (direction: "left" | "right") => {
    if (!carouselRef.current) return;

    const scrollAmount = direction === "left" ? -480 : 480; // Adjust scroll amount as needed
    carouselRef.current.scrollBy({ left: scrollAmount, behavior: "smooth" });
  };

  return (
    <div className="flex flex-col gap-2">
      <div className="font-semibold text-xl">{title}</div>
      <div className="flex items-center  relative py-2 px-5">
        {canScrollLeft && (
          <button
            className="absolute left-0 z-10 p-2 bg-white border border-gray-300 rounded-full shadow-md cursor-pointer hover:bg-gray-100"
            onClick={() => scrollCarousel("left")}
          >
            <ArrowLeftIcon />
          </button>
        )}
        <ul
          ref={carouselRef}
          className="flex overflow-x-auto scroll-smooth no-scrollbar gap-5 justify-around w-full py-2 px-4"
        >
          {players.map((player, index) => (
            <li
              key={player.id}
              className="flex flex-col items-center gap-2
            rounded-lg border border-neutral-200 p-5 flex-1
            hover:cursor-pointer min-w-60
            hover:scale-105 relative hover:font-medium"
              onClick={() => onPlayerClick(player.id)}
            >
              <label className="absolute top-2 left-2">{`#${index + 1}`}</label>
              <img width="100" src={player.imageUrl} alt="" />
              <label>{player.fullName}</label>
              <div className="flex gap-5">
                <label>{`${label}:`}</label>
                <label>{player.value}</label>
              </div>
            </li>
          ))}
        </ul>
        {canScrollRight && (
          <button
            className="absolute right-0 z-10 p-2 bg-white border border-gray-300 rounded-full shadow-md cursor-pointer hover:bg-gray-100"
            onClick={() => scrollCarousel("right")}
          >
            <ArrowRightIcon />
          </button>
        )}
      </div>
    </div>
  );
};

export default PlayerCarousell;
