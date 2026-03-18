package tn.esprit.tic.se.spr01.VideoandStrategic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoAnnotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VideoAnnotationId;

    @Temporal(TemporalType.TIME)
    private Date Timestamp;

    private String Comment;

    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedAt;
    @Enumerated(EnumType.STRING)
    private AnnotationStatus status;
    @Enumerated(EnumType.STRING)
    private AnnotationType annotationType;

    /* @Enumerated(EnumType.STRING)
     private AnnotationStatus Status;

     @Enumerated(EnumType.STRING)
     private AnnotationType annotationType;
 */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private VideoAnalysis videoAnalysisA;

}
