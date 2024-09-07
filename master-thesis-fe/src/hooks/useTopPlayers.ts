import { useMemo } from "react";
import { CarousellPlayer } from "../pages/homepage/components/PlayerCarousell";
import { PlayerAveragesKey, PlayerDetails, usePlayerSort } from "../service";

type UseTopPlayersResponse = {
  mostPointsCarouselPlayers?: CarousellPlayer[];
  mostAssitsCarouselPlayers?: CarousellPlayer[];
  mostMinutesCarouselPlayers?: CarousellPlayer[];
  mostStealsCarouselPlayers?: CarousellPlayer[];
  mostTurnoversCarouselPlayers?: CarousellPlayer[];
  mostReboundsCarouselPlayers?: CarousellPlayer[];
};

const mapPlayerDetailsToCarouselPlayers = (
  playerAveragesKey: PlayerAveragesKey,
  players?: PlayerDetails[]
): CarousellPlayer[] | undefined =>
  players?.map((player) => ({
    id: player.id,
    fullName: player.firstName + " " + player.lastName,
    value: player[playerAveragesKey],
    imageUrl: player.imageUrl,
  }));

export const useTopPlayers = (): UseTopPlayersResponse => {
  const { data: mostPointsPlayers } = usePlayerSort("AVERAGE_POINTS");
  const { data: mostAssitsPlayers } = usePlayerSort("AVERAGE_ASSISTS");
  const { data: mostMinutesPlayers } = usePlayerSort("AVERAGE_MINUTES");
  const { data: averageStealsPlayers } = usePlayerSort("AVERAGE_STEALS");
  const { data: averageTurnoversPlayers } = usePlayerSort("AVERAGE_TURNOVERS");
  const { data: averageReboundsPlayers } = usePlayerSort("AVERAGE_REBOUNDS");

  const mostPointsCarouselPlayers: CarousellPlayer[] | undefined = useMemo(
    () => mapPlayerDetailsToCarouselPlayers("averagePoints", mostPointsPlayers),
    [mostPointsPlayers]
  );

  const mostAssitsCarouselPlayers: CarousellPlayer[] | undefined = useMemo(
    () =>
      mapPlayerDetailsToCarouselPlayers("averageAssists", mostAssitsPlayers),
    [mostAssitsPlayers]
  );

  const mostMinutesCarouselPlayers: CarousellPlayer[] | undefined = useMemo(
    () =>
      mapPlayerDetailsToCarouselPlayers("averageMinutes", mostMinutesPlayers),
    [mostMinutesPlayers]
  );

  const mostStealsCarouselPlayers: CarousellPlayer[] | undefined = useMemo(
    () =>
      mapPlayerDetailsToCarouselPlayers("averageSteals", averageStealsPlayers),
    [averageStealsPlayers]
  );

  const mostTurnoversCarouselPlayers: CarousellPlayer[] | undefined = useMemo(
    () =>
      mapPlayerDetailsToCarouselPlayers(
        "averageTurnovers",
        averageTurnoversPlayers
      ),
    [averageTurnoversPlayers]
  );

  const mostReboundsCarouselPlayers: CarousellPlayer[] | undefined = useMemo(
    () =>
      mapPlayerDetailsToCarouselPlayers(
        "averageRebounds",
        averageReboundsPlayers
      ),
    [averageReboundsPlayers]
  );

  return {
    mostPointsCarouselPlayers,
    mostAssitsCarouselPlayers,
    mostMinutesCarouselPlayers,
    mostStealsCarouselPlayers,
    mostTurnoversCarouselPlayers,
    mostReboundsCarouselPlayers,
  };
};
