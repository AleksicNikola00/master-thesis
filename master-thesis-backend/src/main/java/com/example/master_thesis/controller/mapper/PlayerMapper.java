package com.example.master_thesis.controller.mapper;

import com.example.master_thesis.controller.dto.PlayerDetailedResponseDto;
import com.example.master_thesis.controller.dto.PlayerResponseDto;
import com.example.master_thesis.persistance.model.player.Player;
import com.example.master_thesis.persistance.model.player.projection.PlayerBaseInfoProjection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);


    List<PlayerResponseDto> toResponseDtosFromProjections(List<PlayerBaseInfoProjection> players);

    List<PlayerDetailedResponseDto> toDetailedResponseDtosFromEntities(List<Player> players);
}
