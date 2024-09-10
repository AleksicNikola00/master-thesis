import { ReactElement } from "react";
import { ArticleSummary } from "../service";

type ArticlesProps = {
  articles: ArticleSummary[];
};

export const Articles = ({ articles }: ArticlesProps): ReactElement => {
  return (
    <ul className="flex flex-col gap-10 pt-5 pb-10">
      {articles.map((article) => (
        <li key={article.id}>
          <div
            className="flex mt-5 gap-2 
            shadow-[rgba(0,0,0,0.15)_0px_20px_25px_15px]
            hover:scale-105 hover:cursor-pointer
            pr-10 
            items-center  max-w-[750px]
            rounded-xl"
          >
            <img width={250} height={250} src={article.playerImageUrl} />
            <p>{`${article.articleContentSummary}...`}</p>
          </div>
        </li>
      ))}
    </ul>
  );
};
