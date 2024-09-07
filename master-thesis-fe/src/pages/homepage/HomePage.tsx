import { ReactElement, useState } from "react";
import { useTopPlayers } from "../../hooks";
import PlayerCarousel from "./components/PlayerCarousel";
import {
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
} from "@mui/material";
import { blackInputSx } from "../../components";
import { SortCriteria } from "../../service";

const sortCriteriaMap: Map<SortCriteria, string> = new Map<
  SortCriteria,
  string
>([
  ["AVERAGE_POINTS", "Points"],
  ["AVERAGE_MINUTES", "Minutes"],
  ["AVERAGE_ASSISTS", "Assists"],
  ["AVERAGE_STEALS", "Steals"],
  ["AVERAGE_TURNOVERS", "Turnovers"],
  ["AVERAGE_REBOUNDS", "Rebounds"],
]);

export const HomePage = (): ReactElement => {
  const [selectedSortCriteria, setSelectedSortCriteria] =
    useState<SortCriteria>("AVERAGE_POINTS");
  const { carouselPlayers: carouselPlusMinusPlayers } =
    useTopPlayers("AVERAGE_PLUS_MINUS");
  const { carouselPlayers: carouselSelectedPlayers } =
    useTopPlayers(selectedSortCriteria);

  const handleChange = (event: SelectChangeEvent) => {
    setSelectedSortCriteria(event.target.value as SortCriteria);
  };

  return (
    <div className="flex flex-col gap-5">
      {carouselPlusMinusPlayers && (
        <PlayerCarousel
          title="Top Efficiency:"
          label="Average Efficiency"
          players={carouselPlusMinusPlayers}
        />
      )}
      <FormControl sx={{ ...blackInputSx, width: 170 }} size="small">
        <InputLabel>Sort Criteria</InputLabel>
        <Select
          labelId="demo-select-small-label"
          id="demo-select-small"
          value={selectedSortCriteria}
          label="Sort Criteria"
          onChange={handleChange}
        >
          {Array.from(sortCriteriaMap).map((option) => (
            <MenuItem key={option[0]} value={option[0]}>
              {option[1]}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      {carouselSelectedPlayers && (
        <PlayerCarousel
          title={`Top ${sortCriteriaMap.get(selectedSortCriteria)}:`}
          label={`Average ${sortCriteriaMap.get(selectedSortCriteria)}`}
          players={carouselSelectedPlayers}
        />
      )}
    </div>
  );
};
