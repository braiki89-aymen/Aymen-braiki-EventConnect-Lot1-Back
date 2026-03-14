package tn.esprit.tic.se.spr01.TacticalBoard.Entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class TacticsAnimation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idTacticsAnimation;
    @JdbcTypeCode(SqlTypes.JSON) // Indique à Hibernate que cette colonne est JSON
    @Column(columnDefinition = "JSON") // Spécifie que MySQL doit stocker ce champ en JSON
    String animationData; // simulationData est une chaîne JSON
    String previewUrl;
    Double duration;

    @ManyToOne
    TacticsBoard tacticsBoard;

    public void setAnimationData(String animationData) {
        this.animationData = animationData;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public void setTacticsBoard(TacticsBoard tacticsBoard) {
        this.tacticsBoard = tacticsBoard;
    }
}

