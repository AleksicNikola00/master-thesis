import { ReactElement } from "react";
import SportsBasketballIcon from "@mui/icons-material/SportsBasketball";
import Search from "./Search";

const Header = (): ReactElement => {
  return (
    <div className="flex px-10 py-5  bg-neutral-100 sticky justify-between top-0">
      <div className="flex gap-2 items-center">
        <SportsBasketballIcon />
        <h1 className="text-4xl font-bold">Euroleague Players Wiki</h1>
      </div>
      <Search />
    </div>
  );
};

export default Header;
