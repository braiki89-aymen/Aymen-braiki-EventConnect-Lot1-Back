package tn.esprit.tic.se.spr01.VideoandStrategic.dto;

import lombok.*;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.AnnotationStatus;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.AnnotationType;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoAnnotationDto {
    private Long videoAnnotationId;
    private Date timestamp;
    private String comment;
    private Date createdAt;
    private AnnotationStatus status;
    private AnnotationType annotationType;


}