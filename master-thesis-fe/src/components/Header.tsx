import { ReactElement } from "react";
import SportsBasketballIcon from "@mui/icons-material/SportsBasketball";
import { Search } from "./Search";
import { useNavigate } from "react-router-dom";

export const Header = (): ReactElement => {
  const navigate = useNavigate();

  const onTitleClick = () => {
    navigate("/");
  };

  return (
    <header className="flex px-10 py-5 border-b bg-white border-neutral-200  sticky justify-between top-0">
      <div
        className="flex gap-2 items-center cursor-pointer hover:opacity-80"
        onClick={onTitleClick}
      >
        <SportsBasketballIcon />
        <h1 className="text-2xl font-bold">Euroleague Players Wiki</h1>
      </div>
      <Search />
    </header>
  );
};
