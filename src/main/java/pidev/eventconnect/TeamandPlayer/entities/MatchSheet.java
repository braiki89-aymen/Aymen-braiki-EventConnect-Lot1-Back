package tn.esprit.tic.se.spr01.TeamandPlayer.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@RequiredArgsConstructor
public class MatchSheet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int matchSheetId;
    float possesionPercent;
    int goal;
    int shotOnGoal ;
    String fouls ;
    float notes ;




}
