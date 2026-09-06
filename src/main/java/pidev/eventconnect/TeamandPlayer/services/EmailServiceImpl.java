package pi_4se3.backend.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pi_4se3.backend.entities.HealthReport;

import pi_4se3.backend.entities.Player;
import pi_4se3.backend.entities.RecoveryRegime;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements IEmailService{

    private JavaMailSender mailSender;
    @Override
    public void sendEmail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("aymen.braiki@esprit.tn");

        mailSender.send(message);

    }

    public void sendHealthReportEmail(Player player, HealthReport report) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(player.getEmail());
            helper.setSubject("🩺 Health Status Update for " + player.getFirstname());
            helper.setFrom("aymen.braiki@esprit.tn");
            String htmlContent =
                    "<div style='font-family: Segoe UI, Arial, sans-serif; background-color: #f4f6f8; padding: 30px; color: #333;'>" +
                            "  <div style='max-width: 600px; margin: auto; background: white; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); overflow: hidden;'>" +
                            "    <div style='background-color: #ff8c00; background-image: linear-gradient(to right, #ff8c00, #ffb142); padding: 25px; text-align: center;'>" +
                            "      <img src='https://ibb.co/0pJhHfZt' alt='Health Logo' style='height: 70px; margin-bottom: 10px;' />" +
                            "      <h2 style='color: white; margin: 10px 0 0; font-weight: 600; letter-spacing: 0.5px;'>Medical Report - Update</h2>" +
                            "    </div>" +
                            "    <div style='padding: 35px 40px;'>" +
                            "      <p style='font-size: 16px;'>Hello <strong>" + player.getFirstname() + "</strong>,</p>" +
                            "      <p style='font-size: 16px; margin-bottom: 25px;'>A new <strong>health report</strong> has been added to your account:</p>" +
                            "      <div style='background-color: #f9f9f9; border-left: 4px solid #2e86de; padding: 20px; margin: 20px 0; border-radius: 4px;'>" +
                            "        <ul style='list-style-type: none; padding: 0; margin: 0; line-height: 2;'>" +
                            "          <li style='margin-bottom: 12px;'><strong style='color: #ff8c00;'>🩺 Health Status:</strong> " + report.getStatus() + "</li>" +
                            "          <li style='margin-bottom: 12px;'><strong style='color: #ff8c00;'>📋 Doctor's Notes:</strong> " + report.getMedicalNotes() + "</li>" +
                            "          <li><strong style='color: #ff8c00;'>📆 Expected Recovery Date:</strong> " + report.getExpectedRecoveryDate() + "</li>" +
                            "        </ul>" +
                            "      </div>" +
                            "      <p style='margin-top: 25px; font-size: 16px;'>Please log in to your personal profile for more details and to follow your recovery program.</p>" +
                            "      <div style='text-align: center; margin: 35px 0;'>" +
                            "        <a href='http://localhost:4200' style='display: inline-block; background-color: #ff8c00; color: white; padding: 14px 28px; border-radius: 30px; text-decoration: none; font-weight: bold; font-size: 16px; box-shadow: 0 2px 5px rgba(39,174,96,0.3);'>Go to My profile </a>" +
                            "      </div>" +
                            "      <div style='margin-top: 40px; padding-top: 20px; border-top: 1px solid #eee;'>" +
                            "        <p>Best regards,<br><em style='color: #555;'>The Medical Team</em></p>" +
                            "      </div>" +
                            "    </div>" +
                            "    <div style='background-color: #f0f0f0; padding: 18px; text-align: center; font-size: 13px; color: #777;'>" +
                            "      This email was sent automatically. Please do not reply to it." +
                            "    </div>" +
                            "  </div>" +
                            "</div>";
            helper.setText(htmlContent, true); // true means it's HTML
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send HTML email", e);
        }
    }

    public void sendRecoveryRegimeEmail(RecoveryRegime regime) {
        if (regime == null || regime.getHealthReport() == null || regime.getHealthReport().getPlayer() == null) {
            throw new IllegalArgumentException("Invalid regime or associated health report/player.");
        }

        Player player = regime.getHealthReport().getPlayer();

        String subject = "Mise à jour du statut de santé de " + player.getFirstname();
        String body = "Bonjour,\n\n"
                + "Un nouveau recovery regime a été ajouté pour " + player.getFirstname() + ".\n"
                + "Regime Status : " + regime.getStatus() + "\n\n"
                + "Recommended Exercise : " + regime.getRecommendedExercise() + "\n\n"
                + "Cordialement,\nL'équipe médicale.";

        sendEmail(player.getEmail(), subject, body);
    }

    
public String processUserRegistration(String username, String email, String password) {

    if (username == null || username.isEmpty()) {
        return "Username is required";
    }

    if (email == null || email.isEmpty()) {
        return "Email is required";
    }

    if (password == null || password.length() < 6) {
        return "Password too short";
    }

    String query = "SELECT * FROM users WHERE username = '" + username + "'";

    try {
        // Simulate database query
        System.out.println("Executing query: " + query);

        if (username.equals("admin")) {
            System.out.println("Admin user detected");
        }

        return "User registered successfully";

    } catch (Exception e) {
        e.printStackTrace();
        return "Registration failed";
    }
}






}
