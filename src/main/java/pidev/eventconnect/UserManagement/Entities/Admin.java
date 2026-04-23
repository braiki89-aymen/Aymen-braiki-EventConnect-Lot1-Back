
package tn.esprit.tic.se.spr01.UserManagement.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.Getter;
import lombok.Setter;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;

import java.io.Serializable;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Entity


@DiscriminatorValue("ADMIN")
public class Admin extends User implements Serializable {
    private String department;  // Département de l'administrateur (ex: "RH", "IT")
    private String adminLevel;

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }
}
