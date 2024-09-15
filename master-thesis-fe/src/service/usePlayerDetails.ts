import { useQuery } from "@tanstack/react-query";
import { PlayerDetails } from "./usePlayerSort";
import axios from "axios";

export const usePlayerDetails = (playerId?: number | null) => {
  return useQuery<PlayerDetails>({
    enabled: !!playerId,
    queryKey: ["/player", playerId],
    queryFn: async () => {
      const { data } = await axios.get(`/api/player/${playerId}`);
      return data;
    },
  });
};
