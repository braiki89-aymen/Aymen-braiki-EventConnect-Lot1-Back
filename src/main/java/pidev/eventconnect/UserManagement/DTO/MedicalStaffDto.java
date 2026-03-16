package tn.esprit.tic.se.spr01.UserManagement.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link tn.esprit.tic.se.spr01.UserManagement.Entities.MedicalStaff}
 */
@Getter
public class MedicalStaffDto extends UserDto {
        @NotBlank(message = "Specialization is required")
        String specialization;
        @NotBlank(message = "License number is required")
        String licenseNumber;

        public String getSpecialization() {
                return specialization;
        }

        public String getLicenseNumber() {
                return licenseNumber;
        }
}