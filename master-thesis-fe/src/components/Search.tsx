import { useState, ChangeEvent, ReactElement, useMemo } from "react";
import { Autocomplete, TextField, Box } from "@mui/material"; // Make sure these imports are correct for your setup
import { useDebounce } from "../hooks";
import { usePlayerSearch } from "../service";
import { useNavigate } from "react-router-dom";

type DisplayedPlayer = {
  id: number;
  label: string;
  imageUrl: string;
};

export const Search = (): ReactElement => {
  const navigate = useNavigate();
  const [playerName, setPlayerName] = useState<string>("");
  const debouncedPlayerName = useDebounce<string>(playerName, 500);

  const { data: players } = usePlayerSearch(debouncedPlayerName);

  const displayedPlayers = useMemo<DisplayedPlayer[] | undefined>(
    () =>
      players?.map((player) => ({
        id: player.id,
        label: player.firstName + " " + player.lastName,
        imageUrl: player.imageUrl,
      })),
    [players]
  );

  const emptyOptionsText = useMemo(() => {
    if (!debouncedPlayerName) return "Start typing";

    return "No player found";
  }, [debouncedPlayerName]);

  const onInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPlayerName(event.currentTarget.value);
  };

  const onPlayerClick = (id: number) => {
    navigate(`/player/${id}`);
  };

  return (
    <div className="w-80">
      <Autocomplete
        options={displayedPlayers ?? []}
        autoHighlight
        filterOptions={(options) => options}
        noOptionsText={emptyOptionsText} // This line shows 'No player found' when no options are available
        renderOption={(props, player) => {
          const { key, ...optionProps } = props;
          return (
            <Box key={player.id} component="li" {...optionProps}>
              <div
                onClick={() => onPlayerClick(player.id)}
                className="flex gap-2 items-center"
              >
                <img width="40" src={player.imageUrl} alt="" />
                <div className="flex">{player.label}</div>
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
            autoComplete="off"
            slotProps={{
              htmlInput: {
                ...params.inputProps,
                autoComplete: "off",
              },
            }}
          />
        )}
      />
    </div>
  );
};
