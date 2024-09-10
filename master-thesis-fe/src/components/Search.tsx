import { TextField } from "@mui/material";
import { ChangeEvent, ReactElement, useState } from "react";
import { blackInputSx } from "./AutocompleteSearch";

type SearchProps = {
  initialValue?: string;
  placeholder?: string;
  onEnter: (query: string) => void;
};

export const Search = ({
  onEnter,
  placeholder,
  initialValue = "",
}: SearchProps): ReactElement => {
  const [query, setQuery] = useState<string>(initialValue);

  const onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    setQuery(event.target.value);
  };

  const onKeyDownHandler = (event: { code: string }) => {
    if (event.code === "Enter") {
      onEnter(query);
    }
  };

  return (
    <TextField
      className="min-w w-[520px] rounded-lg"
      size="small"
      sx={blackInputSx}
      id="outlined-search"
      label={placeholder}
      type="search"
      value={query}
      onChange={onChangeHandler}
      onKeyDown={onKeyDownHandler}
      InputProps={{ sx: { borderRadius: "15px" } }}
    />
  );
};
