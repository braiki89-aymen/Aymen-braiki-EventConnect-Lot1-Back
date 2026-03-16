package tn.esprit.tic.se.spr01.UserManagement.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tic.se.spr01.UserManagement.Entities.UserRole;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequest {
    String firstname;
    String lastname;
    String password;
    String email;
    String avatarUrl;
    Date dateOfBirth;
    UserRole roleUser;
    String department;
    String adminLevel;
    String specializationCoach;
    String diplome;
    String specializationMed;
    String licenseNumber;
    String role;
    Integer jerseyNumber;


}
