package tn.esprit.tic.se.spr01.UserManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.health.entities.HealthReport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@DiscriminatorValue("MEDICAL_STAFF")
public class MedicalStaff extends User implements Serializable {
    private String specializationMed;  // Spécialisation du personnel médical (ex: "Médecin", "Kinésithérapeute")
    private String licenseNumber;

    @OneToMany(mappedBy = "medicalStaff" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    List<HealthReport> healthReportList = new ArrayList<>();


}
