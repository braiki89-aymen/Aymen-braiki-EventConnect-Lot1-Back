package pi_4se3.backend.services;

import pi_4se3.backend.entities.HealthReport;
import pi_4se3.backend.entities.Player;
import pi_4se3.backend.entities.RecoveryRegime;

public interface IEmailService {

    public void sendEmail(String to, String subject, String body);
    public void sendHealthReportEmail(Player player, HealthReport report);
    public void sendRecoveryRegimeEmail( RecoveryRegime regime);
}
