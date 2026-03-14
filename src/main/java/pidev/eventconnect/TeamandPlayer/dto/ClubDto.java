package tn.esprit.tic.se.spr01.TeamandPlayer.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tic.se.spr01.TeamandPlayer.entities.Club;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ClubDto {
    int clubId;
    String clubName;
    Integer playerId;
    Integer coachId;

    public static ClubDto toDto(Club entity) {

        if (entity == null) {
            return null;
        }

        return ClubDto.builder()
                .clubId(entity.getClubId())
                .clubName(entity.getClubName())
                .build();

    }




}
