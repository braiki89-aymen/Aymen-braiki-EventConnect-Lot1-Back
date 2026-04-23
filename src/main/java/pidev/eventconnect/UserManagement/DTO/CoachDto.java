package tn.esprit.tic.se.spr01.UserManagement.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link tn.esprit.tic.se.spr01.UserManagement.Entities.Coach}
 */
@Getter
public class CoachDto extends UserDto{
        @NotBlank(message = "Specialization is required")
        private String specializationCoach;

        @NotBlank(message = "Diploma is required")
        private String diplome;


        public String getSpecializationCoach() {
                return specializationCoach;
        }

        public String getDiplome() {
                return diplome;
        }

        public void setSpecializationCoach(String specializationCoach) {
                this.specializationCoach = specializationCoach;
        }

        public void setdiplome(String diplome) {
                this.diplome = diplome;
        }


}
