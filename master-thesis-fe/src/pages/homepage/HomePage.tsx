import { ReactElement } from "react";

import PlayerOverview from "./components/PlayerOverview";
import ArticleDisplay from "./components/ArticleDisplay";

export const HomePage = (): ReactElement => {
  return (
    <div className="flex flex-col gap-5">
      <PlayerOverview />
      <ArticleDisplay />
    </div>
  );
};
