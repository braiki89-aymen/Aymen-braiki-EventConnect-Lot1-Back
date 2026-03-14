package tn.esprit.tic.se.spr01.TacticalBoard.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * DTO for {@link tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsStats}
 */
public record TacticsStatsDto(
        @PositiveOrZero(message = "Usage count cannot be negative")
        Integer usageCount,
        @DecimalMin(value = "0.0", message = "Success rating cannot be less than 0")
        @DecimalMax(value = "100.0", message = "Success rating cannot exceed 100")
        Float averageSuccessRating
)  { }