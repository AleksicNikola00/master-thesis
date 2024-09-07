import { useState, useEffect, ChangeEvent, ReactElement, useMemo } from "react";
import { Autocomplete, TextField, Box } from "@mui/material";
import { useDebounce } from "../hooks";
import { usePlayerSearch } from "../service";
import { useNavigate, useLocation } from "react-router-dom";

type DisplayedPlayer = {
  id: number;
  label: string;
  imageUrl: string;
};

const searchSx = {
  "& .MuiOutlinedInput-root": {
    "&.Mui-focused .MuiOutlinedInput-notchedOutline": {
      borderColor: "black",
    },
    "& .MuiAutocomplete-input": {
      color: "black",
    },
    "&.Mui-focused .MuiAutocomplete-input": {
      color: "black",
    },
  },
  "& .MuiInputLabel-root.Mui-focused": {
    color: "black",
  },
};

export const Search = (): ReactElement => {
  const navigate = useNavigate();
  const location = useLocation();

  const [selectedPlayer, setSelectedPlayer] = useState<DisplayedPlayer | null>(
    null
  );
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

  useEffect(() => {
    if (location.pathname === "/") {
      setPlayerName("");
      setSelectedPlayer(null);
    }
  }, [location]);

  const onTextInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPlayerName(event.currentTarget.value);
  };

  const handleAutocompleteChange = (
    _event: any,
    newSelectedPlayer: DisplayedPlayer | null
  ) => {
    setSelectedPlayer(newSelectedPlayer);

    if (!newSelectedPlayer) {
      setPlayerName("");
      return;
    }

    setPlayerName(newSelectedPlayer.label);
    onPlayerClick(newSelectedPlayer.id);
  };

  const onPlayerClick = (id: number) => {
    navigate(`/player/${id}`);
  };

  return (
    <div className="w-80">
      <Autocomplete
        options={displayedPlayers ?? []}
        value={selectedPlayer}
        onChange={handleAutocompleteChange}
        autoHighlight
        filterOptions={(options) => options}
        noOptionsText={emptyOptionsText}
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
            onChange={onTextInputChange}
            label="Search for a player"
            size="small"
            autoComplete="off"
            slotProps={{
              htmlInput: {
                ...params.inputProps,
                autoComplete: "off",
              },
            }}
            sx={searchSx}
          />
        )}
      />
    </div>
  );
};
