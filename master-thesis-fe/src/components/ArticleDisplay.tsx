import { TextField } from "@mui/material";
import { ChangeEvent, ReactElement, useState } from "react";
import { blackInputSx } from ".";
import { useTopArticles } from "../service";

export const ArticleDisplay = (): ReactElement => {
  const [articleQuery, setArticleQuery] = useState<string>("");
  const { data: topArticles } = useTopArticles();

  const onArticleQueryChange = (event: ChangeEvent<HTMLInputElement>) => {
    setArticleQuery(event.target.value);
  };

  const onKeyDown = (event: { code: string }) => {
    if (event.code === "Enter") {
      console.log("ENTERR");
    }
  };

  return (
    <div className="flex flex-col gap-3 items-center w-100% mt-10">
      <div className="font-semibold self-start text-xl">Explore Articles:</div>
      <TextField
        className="min-w w-[520px] rounded-lg"
        size="small"
        sx={blackInputSx}
        id="outlined-search"
        label="Search articles"
        type="search"
        value={articleQuery}
        onChange={onArticleQueryChange}
        onKeyDown={onKeyDown}
        InputProps={{ sx: { borderRadius: "15px" } }}
      />
      <ul className="flex flex-col gap-10 pt-5 pb-10">
        {topArticles?.map((article) => (
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
    </div>
  );
};
