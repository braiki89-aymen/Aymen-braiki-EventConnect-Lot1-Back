package pi_4se3.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pi_4se3.backend.dto.PerformanceTrackingDto;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PerformanceTracking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     LocalDate dateSuivi;
     int douleur; // échelle de 0 à 10
     int endurance; // score perso, par ex. 0-100
     boolean apteAReprendre; // true si le joueur peut reprendre l'entraînement
     int frequenceCardiaque;
     double poids;
     int indiceFatigue;
     int scoreProgression;
     int tauxRecuperationEstime;
     String messageAvertissement;
    @Enumerated(EnumType.STRING)
    NiveauRisque niveauRisque;
    @ManyToOne ( fetch = FetchType.EAGER)
    @JsonIgnore
    Player player;
    public static PerformanceTracking toEntity (PerformanceTrackingDto dto){
        if(dto == null){
            return null;
        }
        return PerformanceTracking.builder()
                .id(dto.getId())
                .dateSuivi(dto.getDateSuivi())
                .douleur(dto.getDouleur())
                .endurance(dto.getEndurance())
                .apteAReprendre(dto.isApteAReprendre())
                .frequenceCardiaque(dto.getFrequenceCardiaque())
                .poids(dto.getPoids())
                .indiceFatigue(dto.getIndiceFatigue())
                .build();
    }


    public String generateRecommendations() {
        StringBuilder recommandations = new StringBuilder();

        if (douleur > 5) {
            recommandations.append("Reduce exercise intensity .\n");
        }

        if (endurance < 40) {
            recommandations.append("Gradually increase light cardiovascular exercises.\n");
        }

        if (frequenceCardiaque > 100) {
            recommandations.append("Heart rate monitoring during exercise.\n");
        }

        if (indiceFatigue > 5) {
            recommandations.append("Plan additional rest periods.\n");
        }

        if (recommandations.length() == 0) {
            recommandations.append("Continue the current program, good progress.");
        }

        return recommandations.toString();
    }
}
