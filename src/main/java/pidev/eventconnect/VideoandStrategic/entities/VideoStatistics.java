package tn.esprit.tic.se.spr01.VideoandStrategic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VideoStatisticsId;

    private float TotalDistanceCovered;
    private float SprintDistance;
    private float TopSpeed;
    private float PassAccuracy;
    private int  PassNbr;
    private float MinSpeed;

    @OneToOne(mappedBy="videoStatistics")
    @JsonIgnore
    private VideoAnalysis videoAnalysisS;
}
