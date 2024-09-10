import { TextField } from "@mui/material";
import { ChangeEvent, ReactElement, useState } from "react";
import { blackInputSx } from "../../../components";

const ArticleDisplay = (): ReactElement => {
  const [articleQuery, setArticleQuery] = useState<string>("");

  const onArticleQueryChange = (event: ChangeEvent<HTMLInputElement>) => {
    setArticleQuery(event.target.value);
  };
  return (
    <div className="flex flex-col gap-2 items-center w-100% ">
      <TextField
        className="min-w w-[520px] rounded-lg"
        size="small"
        sx={blackInputSx}
        id="outlined-search"
        label="Search articles"
        type="search"
        value={articleQuery}
        onChange={onArticleQueryChange}
        InputProps={{ sx: { borderRadius: "15px" } }}
      />
    </div>
  );
};

export default ArticleDisplay;
