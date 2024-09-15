import { useQuery } from "@tanstack/react-query";
import { ArticleSummary } from "./useTopArticles";
import axios from "axios";

export const useQueryArticles = (query?: string | null) => {
  return useQuery<ArticleSummary[]>({
    enabled: !!query,
    queryKey: ["articles", query],
    queryFn: async () => {
      const { data } = await axios.get("/api/article", { params: { query } });
      return data;
    },
  });
};
