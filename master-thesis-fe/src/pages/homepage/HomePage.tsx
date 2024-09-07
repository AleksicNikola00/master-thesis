import { ReactElement, useMemo } from "react";
import { usePlayerSort } from "../../service/usePlayerSort";
import PlayerCarousell, { CarousellPlayer } from "./components/PlayerCarousell";

export const HomePage = (): ReactElement => {
  const { data: mostPointsPlayers } = usePlayerSort("AVERAGE_POINTS");

  const displayedMostPointPlayers: CarousellPlayer[] | undefined = useMemo(
    () =>
      mostPointsPlayers?.map((player) => ({
        id: player.id,
        fullName: player.firstName + " " + player.lastName,
        label: "Points",
        value: player.averagePoints,
        imageUrl: player.imageUrl,
      })),
    [mostPointsPlayers]
  );
  return (
    <>
      {displayedMostPointPlayers && (
        <PlayerCarousell
          title="Most points:"
          players={displayedMostPointPlayers}
        />
      )}
    </>
  );
};
