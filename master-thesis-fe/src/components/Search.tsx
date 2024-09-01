import { Autocomplete, Box, TextField } from "@mui/material";
import { ReactElement } from "react";

const options = [
  {
    label: "Tyler Dorsy",
    src: "https://media-cdn.incrowdsports.com/24f75c32-e4db-499d-b2e6-d46f52986033.png",
  },
  {
    label: "Kevin Spacey",
    src: "https://media-cdn.incrowdsports.com/24f75c32-e4db-499d-b2e6-d46f52986033.png",
  },
];

const Search = (): ReactElement => {
  return (
    <div className="w-80 ">
      <Autocomplete
        freeSolo
        sx={{ width: 300 }}
        options={options}
        autoHighlight
        filterOptions={(options, _) => options}
        renderOption={(props, option) => {
          const { key, ...optionProps } = props;
          return (
            <Box key={key} component="li" {...optionProps}>
              <div className="flex gap-2 items-center">
                <img loading="lazy" width="40" src={option.src} alt="" />
                <div className="flex">{option.label}</div>
              </div>
            </Box>
          );
        }}
        renderInput={(params) => (
          <TextField
            {...params}
            label="Search for a player"
            size="small"
            slotProps={{
              htmlInput: {
                ...params.inputProps,
                autoComplete: "new-password", // disable autocomplete and autofill
              },
            }}
          />
        )}
      />
    </div>
  );
};

export default Search;
