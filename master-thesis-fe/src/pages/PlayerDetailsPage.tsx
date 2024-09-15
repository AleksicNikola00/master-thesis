import { ReactElement } from "react";
import { useParams } from "react-router-dom";
import { usePlayerDetails, useQueryArticles } from "../service";
import { CircularProgress } from "@mui/material";
import { Articles, CircleLabel } from "../components";

export const PlayerDetailsPage = (): ReactElement => {
  const { id: playerId } = useParams();
  const { data: playerDetails, isLoading } = usePlayerDetails(
    playerId ? +playerId : null
  );
  const { data: queriedArticles } = useQueryArticles(
    playerDetails?.firstName + " " + playerDetails?.lastName
  );

  if (isLoading)
    return (
      <div className="absolute top-1/2 left-1/2">
        <CircularProgress size={80} color="inherit" />
      </div>
    );

  return (
    <div className="flex flex-col gap-20">
      <div className="flex items-center gap-10 justify-center">
        <img className="w-[600px] h-[800px]" src={playerDetails?.imageUrl} />
        <div className="flex flex-col text-2xl gap-20">
          <div className="flex flex-col font-medium">
            <label className="text-4xl">{playerDetails?.firstName}</label>
            <label className="text-8xl">{playerDetails?.lastName}</label>
          </div>
          <div className="flex flex-col items-center gap-10">
            <div className="flex gap-10">
              <CircleLabel label="Pts" value={playerDetails?.averagePoints} />
              <CircleLabel
                label="Eff"
                value={playerDetails?.averagePlusMinus}
              />
            </div>
            <div className="flex gap-10">
              <CircleLabel label="Min" value={playerDetails?.averageMinutes} />
              <CircleLabel label="Stl" value={playerDetails?.averageSteals} />
            </div>
            <div className="flex gap-10">
              <CircleLabel label="Ass" value={playerDetails?.averageAssists} />
              <CircleLabel label="Reb" value={playerDetails?.averageRebounds} />
            </div>
          </div>
        </div>
      </div>
      <div className="flex flex-col w-100 items-center">
        <div className="font-semibold self-start text-xl">
          Related Articles:
        </div>
        {queriedArticles && <Articles articles={queriedArticles} />}
      </div>
    </div>
  );
};
