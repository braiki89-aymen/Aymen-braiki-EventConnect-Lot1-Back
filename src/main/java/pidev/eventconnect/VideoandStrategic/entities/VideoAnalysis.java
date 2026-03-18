package tn.esprit.tic.se.spr01.VideoandStrategic.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoAnalysisId;

    private String title;

    private String description;

    private String videoUrl;

    @Enumerated(EnumType.STRING)
    private VideoAnalysisType analysisType;

    @Enumerated(EnumType.STRING)
    private VideoStatus status;

    @OneToOne
    private VideoStatistics videoStatistics;
}
