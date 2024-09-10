// String id,
// String playerFirstName,
// String playerLastName,
// String playerImageUrl,
// String articleContentSummary

import { useQuery } from "@tanstack/react-query";
import axios from "axios";

export type ArticleSummary = {
  id: string;
  playerFirstName: string;
  playerLastName: string;
  playerImageUrl: string;
  articleContentSummary: string;
};

export const useTopArticles = () => {
  return useQuery<ArticleSummary[]>({
    queryKey: ["articles", "popular"],
    queryFn: async () => {
      const { data } = await axios.get("/api/article/popular");
      return data;
    },
  });
};
