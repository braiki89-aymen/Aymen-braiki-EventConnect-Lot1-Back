package tn.esprit.tic.se.spr01.Communication.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link tn.esprit.tic.se.spr01.Communication.Entities.Chat}
 */
public record ChatDto(
        @NotNull(message = "Sender ID is required")
        Long idSender,
        @NotNull(message = "Receiver ID is required")
        Long idReceiver,
        @Size(message = "Message must be less than 1000 characters", max = 1000)
        @NotBlank(message = "Message is required")
        String message,
        @PastOrPresent(message = "Date cannot be in the future")
        LocalDate date
) { }