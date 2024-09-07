import { useMemo } from "react";
import { CarousellPlayer } from "../pages/homepage/components/PlayerCarousel";
import {
  PlayerAveragesKey,
  PlayerDetails,
  SortCriteria,
  usePlayerSort,
} from "../service";
import { toCamelCase } from "../utils";

type UseTopPlayersResponse = {
  carouselPlayers?: CarousellPlayer[];
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

const mapSortCriteriaToPlayerAveragesKey = (
  sortCriteria: SortCriteria
): PlayerAveragesKey =>
  toCamelCase(sortCriteria.toString()) as PlayerAveragesKey;

export const useTopPlayers = (
  sortCriteria: SortCriteria
): UseTopPlayersResponse => {
  const { data: topPlayers } = usePlayerSort(sortCriteria);

  const carouselPlayers: CarousellPlayer[] | undefined = useMemo(
    () =>
      mapPlayerDetailsToCarouselPlayers(
        mapSortCriteriaToPlayerAveragesKey(sortCriteria),
        topPlayers
      ),
    [topPlayers]
  );

  return {
    carouselPlayers,
  };
};
