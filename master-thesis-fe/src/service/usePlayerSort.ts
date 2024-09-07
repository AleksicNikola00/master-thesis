import { useQuery } from "@tanstack/react-query";
import axios from "axios";
import { Player } from "./usePlayerSearch";

type PlayerAverages = {
  averageMinutes: number;
  averagePoints: number;
  averageRebounds: number;
  averageAssists: number;
  averageSteals: number;
  averageTurnovers: number;
};

export type PlayerAveragesKey = keyof PlayerAverages;

export type PlayerDetails = Player & PlayerAverages;

export type SortCriteria =
  | "AVERAGE_MINUTES"
  | "AVERAGE_POINTS"
  | "AVERAGE_ASSISTS"
  | "AVERAGE_STEALS"
  | "AVERAGE_TURNOVERS"
  | "AVERAGE_REBOUNDS";

export const usePlayerSort = (sortCriteria: SortCriteria) => {
  return useQuery<PlayerDetails[]>({
    enabled: !!sortCriteria,
    queryKey: ["players", "sorted", sortCriteria],
    queryFn: async () => {
      const { data } = await axios.get(`/api/player/sorted`, {
        params: { sortCriteria },
      });
      return data;
    },
    staleTime: 300_000,
  });
};
