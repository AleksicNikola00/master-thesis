import { useQuery } from "@tanstack/react-query";
import axios from "axios";

export type Player = {
  id: number;
  firstName: string;
  lastName: string;
  imageUrl: string;
};

export const usePlayerSearch = (playerName: string) => {
  return useQuery<Player[]>({
    enabled: !!playerName,
    queryKey: ["players", playerName.toLowerCase()],
    queryFn: async () => {
      const { data } = await axios.get(`/api/player`, {
        params: { playerName },
      });
      return data;
    },
  });
};
