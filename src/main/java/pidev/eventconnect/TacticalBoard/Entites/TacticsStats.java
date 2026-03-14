package tn.esprit.tic.se.spr01.TacticalBoard.Entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class TacticsStats implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idTacticStat;
    Integer usageCount;
    Float averageSuccessRating;
    @ManyToMany(mappedBy = "tacticsStats")
    Set<TacticsBoard> tacticsBoards;

     public void setUsageCount(Integer usageCount) {
        this.usageCount = usageCount;
    }

    public void setAverageSuccessRating(Float averageSuccessRating) {
        this.averageSuccessRating = averageSuccessRating;
    }
}
