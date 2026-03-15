package tn.esprit.tic.se.spr01.TeamandPlayer.dto;

import lombok.*;

import lombok.experimental.FieldDefaults;

import tn.esprit.tic.se.spr01.TeamandPlayer.entities.Game;


import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class GameDto {

    int matchId;
    Date matchDate;
    String status;


public static GameDto toDto(Game entity) {
    if (entity == null) {
        return null;
    }

    return GameDto.builder()
            .matchId(entity.getMatchId())
            .matchDate(entity.getMatchDate())
            .status(entity.getStatus())
            .build();


}


}
