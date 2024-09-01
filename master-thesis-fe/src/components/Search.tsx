import { Autocomplete, Box, TextField } from "@mui/material";
import { ChangeEvent, ReactElement, useState } from "react";
import { usePlayerSearch } from "../service";
import { useDebounce } from "../hooks";

const Search = (): ReactElement => {
  const [playerName, setPlayerName] = useState<string>("");
  const debouncedPlayerName = useDebounce<string>(playerName, 500);

  const { data } = usePlayerSearch(debouncedPlayerName);

  const onInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPlayerName(event.currentTarget.value);
  };

  return (
    <div className="w-80 ">
      <Autocomplete
        freeSolo
        noOptionsText="No player found"
        options={data ?? []}
        autoHighlight
        filterOptions={(options, _) => options}
        renderOption={(props, player) => {
          const { key, ...optionProps } = props;
          return (
            <Box key={key} component="li" {...optionProps}>
              <div className="flex gap-2 items-center">
                <img loading="lazy" width="40" src={player.imageUrl} alt="" />
                <div className="flex">{`${player.firstName} ${player.lastName}`}</div>
              </div>
            </Box>
          );
        }}
        renderInput={(params) => (
          <TextField
            {...params}
            value={playerName}
            onChange={onInputChange}
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
