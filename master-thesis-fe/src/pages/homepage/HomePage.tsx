import { ReactElement } from "react";

import PlayerOverview from "./components/PlayerOverview";
import { ArticleDisplay } from "../../components";

export const HomePage = (): ReactElement => {
  return (
    <div className="flex flex-col gap-10">
      <div className="flex flex-col gap-5">
        <PlayerOverview />
      </div>
      <ArticleDisplay />
    </div>
  );
};
