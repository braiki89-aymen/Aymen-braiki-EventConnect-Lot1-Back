package tn.esprit.tic.se.spr01.VideoandStrategic.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoAnalysisType;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoAnalysisDto {
    private Long videoAnalysisId;

    @NotNull(message = "Le titre ne peut pas être vide.")
    @Size(min = 3, max = 100, message = "Le titre doit contenir entre 3 et 100 caractères.")
    private String title;

    @NotNull(message = "La description ne peut pas être vide.")
    @Size(min = 10, max = 500, message = "La description doit contenir entre 10 et 500 caractères.")
    private String description;

    @NotNull(message = "L'URL de la vidéo ne peut pas être vide.")
    private String videoUrl;

    @NotNull(message = "Le type d'analyse est requis.")
    private VideoAnalysisType analysisType;

    @NotNull(message = "Le statut est requis.")
    private VideoStatus status;
}
