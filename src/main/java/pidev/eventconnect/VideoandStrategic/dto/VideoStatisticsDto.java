package tn.esprit.tic.se.spr01.VideoandStrategic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoStatisticsDto {
    private Long videoStatisticsId;
    private float totalDistanceCovered;
    private float sprintDistance;
    private float topSpeed;
    private float passAccuracy;
    private int  PassNbr;
    private float MinSpeed;
}
