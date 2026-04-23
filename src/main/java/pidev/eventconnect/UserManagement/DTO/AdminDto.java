package tn.esprit.tic.se.spr01.UserManagement.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link tn.esprit.tic.se.spr01.UserManagement.Entities.Admin}
 */

public class AdminDto extends UserDto{
    @NotBlank(message = "Department is required")
    String department;
    @NotBlank(message = "Admin level is required")
    String adminLevel;

    public String getDepartment() {
        return department;
    }

    public String getAdminLevel() {
        return adminLevel;
    }
}