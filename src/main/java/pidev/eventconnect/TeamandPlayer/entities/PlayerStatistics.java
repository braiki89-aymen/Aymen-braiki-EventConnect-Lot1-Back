
package tn.esprit.tic.se.spr01.TeamandPlayer.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

@RequiredArgsConstructor

public class PlayerStatistics implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int plystaId;
    float performanceRate ;
    Date date ;
    int goalsScored ;

    int assits ;






}
