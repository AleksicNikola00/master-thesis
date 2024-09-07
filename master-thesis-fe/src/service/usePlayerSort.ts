import { useQuery } from "@tanstack/react-query";
import axios from "axios";
import { Player } from "./usePlayerSearch";

type PlayerDetails = Player & {
  averageMinutes: number;
  averagePoints: number;
  averageRebounds: number;
  averageAssits: number;
  averageSteals: number;
  averageTurnovers: number;
};

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
  });
};
