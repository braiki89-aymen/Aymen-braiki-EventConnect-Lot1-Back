package pi_4se3.backend.dto;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pi_4se3.backend.entities.*;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportDto {
    long healthId;
    @NotNull(message = "injury type required !")
    String injuryType;
    @NotNull(message = "injury severity required !")
    TypeInjurySeverity injurySeverity;
    @NotNull(message = "diagnosis date required !")
    LocalDate diagnosisDate;
    @Future(message = "The recovery date must be after the diagnosis date !")
    LocalDate expectedRecoveryDate;
    @NotNull(message = "recovery Plan required !")
    String recoveryPlan;
    @Pattern(regexp = "^(Low|Medium|High)$", message = "Fitness Level must be : 'Low', 'Medium' ou 'High'.")
    String fitnessLevel;
    @NotNull(message = "medical notes required !")
    String medicalNotes;
    @NotNull(message = "status required !")
    TypeStatusHealth status;
    Long staffId;
    Long playerId;
    Long regimeId;
    String firstNameMed;
    String firstNamePlay;

    public static ReportDto toDto(HealthReport report) {

        if (report == null) {
            return null;
        }
        return ReportDto.builder()
                .healthId(report.getHealthId())
                .staffId(report.getMedicalStaff()!= null ? report.getMedicalStaff().getId() : null)
                .playerId(report.getPlayer()!= null ? report.getPlayer().getId(): null)
                .regimeId(report.getRecoveryRegime() != null ? report.getRecoveryRegime().getRegimeId() : null)
                .injurySeverity(report.getInjurySeverity())
                .injuryType(report.getInjuryType())
                .diagnosisDate(report.getDiagnosisDate())
                .expectedRecoveryDate(report.getExpectedRecoveryDate())
                .recoveryPlan(report.getRecoveryPlan())
                .fitnessLevel(report.getFitnessLevel())
                .medicalNotes(report.getMedicalNotes())
                .status(report.getStatus())
                .firstNameMed(report.getMedicalStaff() != null ? report.getMedicalStaff().getFirstname() : null)
                .firstNamePlay(report.getPlayer() != null ? report.getPlayer().getFirstname() : null)
                .build();
    }
}