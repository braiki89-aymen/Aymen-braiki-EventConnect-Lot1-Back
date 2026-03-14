package tn.esprit.tic.se.spr01.Communication.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tn.esprit.tic.se.spr01.Communication.Entities.StatusNotif;

import java.io.Serializable;

/**
 * DTO for {@link tn.esprit.tic.se.spr01.Communication.Entities.Notification}
 */
public record NotificationDto(
        @Size(message = "Content must be less than 500 characters", max = 500)
        @NotBlank(message = "Content is required")
        String content,
        @NotNull(message = "Status is required")
        StatusNotif status
){}