import { ReactElement } from "react";
import { useTopPlayers } from "../../hooks";
import PlayerCarousell from "./components/PlayerCarousell";

export const HomePage = (): ReactElement => {
  const {
    mostPointsCarouselPlayers,
    mostAssitsCarouselPlayers,
    mostMinutesCarouselPlayers,
    mostStealsCarouselPlayers,
    mostTurnoversCarouselPlayers,
    mostReboundsCarouselPlayers,
  } = useTopPlayers();

  return (
    <div className="flex flex-col gap-5">
      {mostPointsCarouselPlayers && (
        <PlayerCarousell
          title="Most points:"
          label="Average Points"
          players={mostPointsCarouselPlayers}
        />
      )}
      {mostAssitsCarouselPlayers && (
        <PlayerCarousell
          title="Most assists:"
          label="Average Assists"
          players={mostAssitsCarouselPlayers}
        />
      )}
      {mostMinutesCarouselPlayers && (
        <PlayerCarousell
          title="Most minutes:"
          label="Average Minutes"
          players={mostMinutesCarouselPlayers}
        />
      )}
      {mostStealsCarouselPlayers && (
        <PlayerCarousell
          title="Most steals:"
          label="Average Steals"
          players={mostStealsCarouselPlayers}
        />
      )}
      {mostTurnoversCarouselPlayers && (
        <PlayerCarousell
          title="Most turnovers:"
          label="Average Turnovers"
          players={mostTurnoversCarouselPlayers}
        />
      )}
      {mostReboundsCarouselPlayers && (
        <PlayerCarousell
          title="Most rebounds:"
          label="Average Rebounds"
          players={mostReboundsCarouselPlayers}
        />
      )}
    </div>
  );
};
