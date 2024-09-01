package com.example.master_thesis.api.mapper;

import com.example.master_thesis.api.dto.PlayerStatisticsDto;
import com.example.master_thesis.persistance.model.Game;
import com.example.master_thesis.persistance.model.player.Player;
import com.example.master_thesis.persistance.model.player.PlayerGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerGameMapper {
    PlayerGameMapper INSTANCE = Mappers.getMapper(PlayerGameMapper.class);
    String DID_NOT_PLAY = "DNP";
    String MINUTES_SEPARATOR = ":";

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "player", source = "player")
    @Mapping(target = "game", source = "game")
    @Mapping(target = "minutes", source = "dto.minutes", qualifiedByName = "stringToInt")
    @Mapping(target = "_2PointsMade", source = "dto.fieldGoalsMade2")
    @Mapping(target = "_2PointsAttempted", source = "dto.fieldGoalsAttempted2")
    @Mapping(target = "_3PointsMade", source = "dto.fieldGoalsMade3")
    @Mapping(target = "_3PointsAttempted", source = "dto.fieldGoalsAttempted3")
    @Mapping(target = "ftMade", source = "dto.freeThrowsMade")
    @Mapping(target = "ftAttempted", source = "dto.freeThrowsAttempted")
    @Mapping(target = "rebounds", source = "dto.totalRebounds")
    PlayerGame toEntity(PlayerStatisticsDto dto, Game game, Player player);

    @Named("stringToInt")
    static int stringToInt(String value) {
        if (value == null || value.isBlank() || value.equals(DID_NOT_PLAY))
            return 0;
        return Integer.parseInt(value.split(MINUTES_SEPARATOR)[0]);
    }
}
