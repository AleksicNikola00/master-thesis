import { useQuery } from "@tanstack/react-query";
import axios from "axios";

export type ArticleDetails = {
  id: string;
  playerId: number;
  playerFirstName: string;
  playerLastName: string;
  articleUrl: string;
  articleContent: string;
  playerImageUrl: string;
};

export const useArticleDetails = (articleId?: string | null) => {
  return useQuery<ArticleDetails>({
    enabled: !!articleId,
    queryKey: ["article", articleId],
    queryFn: async () => {
      const { data } = await axios.get(`/api/article/${articleId}`);
      return data;
    },
  });
};
