import { ReactElement } from "react";
import { ArticleSummary } from "../service";
import { useNavigate } from "react-router-dom";
import { toInnerTextHtml } from "../utils";

type ArticlesProps = {
  articles: ArticleSummary[];
};

export const Articles = ({ articles }: ArticlesProps): ReactElement => {
  const navigate = useNavigate();

  return (
    <ul className="flex flex-col gap-10 pt-5 pb-10">
      {articles.map((article) => (
        <li
          onClick={() => navigate(`/article/${article.id}`)}
          key={article.id}
          className="flex mt-5 gap-2 
        shadow-[rgba(0,0,0,0.15)_0px_20px_25px_15px]
        hover:scale-105 hover:cursor-pointer
        pr-10 
        items-center  max-w-[750px]
        rounded-xl"
        >
          <img width={250} height={250} src={article.playerImageUrl} />
          <div
            dangerouslySetInnerHTML={{
              __html: toInnerTextHtml(article.articleContentSummary),
            }}
          ></div>
        </li>
      ))}
    </ul>
  );
};
