package tn.esprit.tic.se.spr01.TeamandPlayer.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import tn.esprit.tic.se.spr01.TeamandPlayer.entities.GameSheet;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameSheetDto implements Serializable {
    int matchSheetId;
    float possessionPercent;
    int goal;
    int shotOnGoal;
    String fouls;
    float notes;

    public static GameSheetDto toDto(GameSheet entity) {
        if (entity == null) {
            return null;
        }
        return GameSheetDto.builder()
                .matchSheetId(entity.getMatchSheetId())
                .possessionPercent(entity.getPossesionPercent())
                .goal(entity.getGoal())
                .shotOnGoal(entity.getShotOnGoal())
                .fouls(entity.getFouls())
                .notes(entity.getNotes())
                .build();
    }


    public static GameSheet toEntity(GameSheetDto dto) {
        if (dto == null) {
            return null;
        }
        return GameSheet.builder()
                .matchSheetId(dto.getMatchSheetId())
                .possesionPercent(dto.getPossessionPercent())
                .goal(dto.getGoal())
                .shotOnGoal(dto.getShotOnGoal())
                .fouls(dto.getFouls())
                .notes(dto.getNotes())
                .build();
    }
}
