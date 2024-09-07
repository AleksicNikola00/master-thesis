import { useNavigate } from "react-router-dom";

export type CarousellPlayer = {
  id: number;
  label: string;
  value: any;
  fullName: string;
  imageUrl: string;
};

type PlayerCarousellProps = {
  title: string;
  players: CarousellPlayer[];
};

const PlayerCarousell = ({ title, players }: PlayerCarousellProps) => {
  const navigate = useNavigate();

  const onPlayerClick = (playerId: number) => {
    navigate(`/player/${playerId}`);
  };

  return (
    <div className="flex flex-col gap-2">
      <div className="font-semibold text-xl">{title}</div>
      <ul className="flex gap-5 justify-around">
        {players.map((player, index) => (
          <li
            key={player.id}
            className="flex flex-col items-center gap-2
            rounded-lg border border-neutral-200 p-5 flex-1
            hover:cursor-pointer
            hover:scale-105 relative hover:font-medium"
            onClick={() => onPlayerClick(player.id)}
          >
            <label className="absolute top-2 left-2">{`#${index + 1}`}</label>
            <img width="100" src={player.imageUrl} alt="" />
            <label>{player.fullName}</label>
            <div className="flex gap-5">
              <label>{`${player.label}:`}</label>
              <label>{player.value}</label>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default PlayerCarousell;
