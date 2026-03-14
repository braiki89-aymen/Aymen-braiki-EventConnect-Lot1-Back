package pi_4se3.backend.dto;
import com.fasterxml.jackson.databind.node.LongNode;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pi_4se3.backend.entities.RecoveryRegime;
import pi_4se3.backend.entities.TypeStatusRegime;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class RegimeDto {
    long regimeId;
    @NotNull(message = "generated Date required !")
    LocalDate generatedAt;
    @NotNull(message = "Daily duration is required.")
    @Min(value = 1, message = "Daily duration must be a positive number.")
    int dailyDuration;
    @NotNull(message = "recommended Exercice required !")
    String recommendedExercise;
    @NotNull(message = "exercice intensity required !")
    String exerciseIntensity;
    @NotNull(message = "diet recommendation required !")
    String dietRecommendation;
    boolean physiotherapyRequired;
    @NotNull(message = "progress tracking required !")
    String progressTracking;
    @NotNull(message = "Status required !")
    TypeStatusRegime status;
    long healthId;
    String firstname;
    Long playerId;

    public static RegimeDto toDto(RecoveryRegime entity) {
        if (entity == null) {
            return null;
        }

        return RegimeDto.builder()
                .regimeId(entity.getRegimeId())
                .generatedAt(entity.getGeneratedAt())
                .dailyDuration(entity.getDailyDuration())
                .recommendedExercise(entity.getRecommendedExercise())
                .exerciseIntensity(entity.getExerciseIntensity())
                .dietRecommendation(entity.getDietRecommendation())
                .physiotherapyRequired(entity.isPhysiotherapyRequired())
                .progressTracking(entity.getProgressTracking())
                .status(entity.getStatus())
                .healthId(entity.getHealthReport() != null ? entity.getHealthReport().getHealthId() : 0L)
                .firstname(entity.getHealthReport()!= null && entity.getHealthReport().getPlayer()!=null ?
                        entity.getHealthReport().getPlayer().getFirstname() :null)
                .playerId(entity.getHealthReport()!=null && entity.getHealthReport().getPlayer()!=null ?
                        entity.getHealthReport().getPlayer().getId() : 0L)
                .build();
    }


}
