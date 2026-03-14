package tn.esprit.tic.se.spr01.TacticalBoard.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsAnimation}
 */
public record TacticsAnimationDto(
        @NotBlank(message = "Animation data is required")
        String animationData,

        String previewUrl,
        @Positive(message = "Duration must be positive")
        Double duration,
        @NotNull(message = "Tactics Board ID is required")
        Long tacticsBoardIdTactic
){}