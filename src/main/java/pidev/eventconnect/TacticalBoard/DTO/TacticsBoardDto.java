package tn.esprit.tic.se.spr01.TacticalBoard.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Map;

/**
 * DTO for {@link tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsBoard}
 */
public record TacticsBoardDto(
        @Size(message = "Name must be less than 100 characters", max = 100)
        @NotBlank(message = "Name is required")
        String name,
        @PastOrPresent(message = "Creation date cannot be in the future")
        LocalDate createdAt
//        @NotNull(message = "Coach ID is required")
//        Long coachId


){}