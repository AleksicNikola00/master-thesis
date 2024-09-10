import { ReactElement } from "react";
import { useTopArticles } from "../../../service";
import { createSearchParams, useNavigate } from "react-router-dom";
import { routes } from "../../../router";
import { Search } from "../../../components/Search";
import { Articles } from "../../../components/Articles";

export const ArticleDisplay = (): ReactElement => {
  const navigate = useNavigate();
  const { data: topArticles } = useTopArticles();

  const onKeyDown = (query: string) => {
    navigate({
      pathname: routes.ARTICLE_PATH,
      search: createSearchParams({
        query,
      }).toString(),
    });
  };

  return (
    <div className="flex flex-col gap-3 items-center w-100%">
      <div className="font-semibold self-start text-xl">Explore Articles:</div>
      <Search onEnter={onKeyDown} placeholder="Search Articles" />
      {topArticles && <Articles articles={topArticles} />}
    </div>
  );
};
