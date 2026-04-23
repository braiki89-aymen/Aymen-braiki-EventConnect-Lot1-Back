package tn.esprit.tic.se.spr01.UserManagement.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link tn.esprit.tic.se.spr01.UserManagement.Entities.Player}
 */
@Getter
public class PlayerDto extends UserDto{
        @NotBlank(message = "Position is required")
        String position;
        @Min(message = "Jersey number must be ≥ 1", value = 1)
        @Max(message = "Jersey number must be ≤ 99", value = 99)
        Integer jerseyNumber;
        String healthStatus;

        public String getPosition() {
                return position;
        }

        public Integer getJerseyNumber() {
                return jerseyNumber;
        }

        public String getHealthStatus() {
                return healthStatus;
        }
}