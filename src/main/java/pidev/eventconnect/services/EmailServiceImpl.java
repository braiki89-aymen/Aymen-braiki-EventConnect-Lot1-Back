package pidev.eventconnect.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pidev.eventconnect.entities.Reservation;

import java.io.File;
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

    @Override
    public void sendConfirmationEmail(Reservation reservation) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(reservation.getEmailParticipant());
            helper.setSubject("✅ Reservation Confirmed - " + reservation.getEvent().getTitle());
            helper.setFrom("braikiaymen89@gmail.com");

            String htmlContent =
                    "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; background: #ffffff; border: 1px solid #ddd; border-radius: 10px; overflow: hidden;'>"

                            + "  <div style='background: #28a745; padding: 15px; text-align: center;'>"
                            + "    <img src='cid:logoImage' alt='EventConnect Logo' style='height: 50px;'/>"
                            + "  </div>"

                            + "  <div style='padding: 20px;'>"
                            + "    <h2 style='color: #28a745; text-align: center;'>🎉 Reservation Confirmed!</h2>"
                            + "    <p>Hello <strong>" + reservation.getFirstNameParticipant() + " " + reservation.getLastNameParticipant() + "</strong>,</p>"
                            + "    <p>You have successfully reserved <strong>" + reservation.getNbPlace() + "</strong> place(s) for the event:</p>"
                            + "    <div style='background:#f8f9fa; padding: 10px; border-radius: 5px; margin: 10px 0; text-align:center;'>"
                            + "      <h3 style='margin:0;'>" + reservation.getEvent().getTitle() + "</h3>"
                            + "    </div>"
                            + "    <p><strong>Cancellation Code:</strong> <span style='color:#dc3545;'>" + reservation.getCancelCode() + "</span></p>"
                            + "    <p>Please present this QR code at the entrance:</p>"
                            + "    <div style='text-align: center; margin: 20px 0;'>"
                            + "      <img src='cid:qrCodeImage' alt='QR Code' style='max-width:200px;'/>"
                            + "    </div>"
                            + "    <p style='font-size: 14px; color: #666;'>If you need to cancel, use your cancellation code.</p>"
                            + "  </div>"

                            + "  <div style='background:#f1f1f1; padding: 10px; text-align: center; font-size: 12px; color:#555;'>"
                            + "    © 2025 EventConnect - All rights reserved"
                            + "  </div>"
                            + "</div>";


            helper.setText(htmlContent, true);

            FileSystemResource logo = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
            helper.addInline("logoImage", logo);

            FileSystemResource qrImage = new FileSystemResource(new File(reservation.getQrCodeBase64()));
            helper.addInline("qrCodeImage", qrImage);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send confirmation email", e);
        }
    }

    @Override
    public void sendWaitingEmail(Reservation reservation) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(reservation.getEmailParticipant());
            helper.setSubject("⏳ Waiting List - " + reservation.getEvent().getTitle());
            helper.setFrom("braikiaymen89@gmail.com");

            String htmlContent =
                    "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; background: #ffffff; border: 1px solid #ddd; border-radius: 10px; overflow: hidden;'>"

                            + "  <div style='background: #ffc107; padding: 15px; text-align: center;'>"
                            + "    <img src='cid:logoImage' alt='EventConnect Logo' style='height: 50px;'/>"
                            + "  </div>"

                            + "  <div style='padding: 20px;'>"
                            + "    <h2 style='color: #856404; text-align: center;'>⏳ You are on the Waiting List</h2>"
                            + "    <p>Hello <strong>" + reservation.getFirstNameParticipant() + " " + reservation.getLastNameParticipant() + "</strong>,</p>"
                            + "    <p>The event <strong>" + reservation.getEvent().getTitle() + "</strong> is currently <span style='color:#dc3545;'>full</span>.</p>"
                            + "    <div style='background:#fff3cd; padding: 10px; border-radius: 5px; margin: 10px 0; text-align:center;'>"
                            + "      <h3 style='margin:0;'>You have been placed on the <strong>waiting list</strong>.</h3>"
                            + "    </div>"
                            + "    <p>We will notify you if a spot becomes available.</p>"
                            + "    <p style='font-size: 14px; color: #666;'>Thank you for your patience and interest in our events.</p>"
                            + "  </div>"

                            + "  <div style='background:#f1f1f1; padding: 10px; text-align: center; font-size: 12px; color:#555;'>"
                            + "    © 2025 EventConnect - All rights reserved"
                            + "  </div>"
                            + "</div>";

            helper.setText(htmlContent, true);
            FileSystemResource logo = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
            helper.addInline("logoImage", logo);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send waiting email", e);
        }
    }

    @Override
    public void sendDiscountCodeEmail(String emailParticipant, String firstNameParticipant, String lastNameParticipant,
                                      String discountCode) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");


            helper.setTo(emailParticipant);
            helper.setSubject("🎁 Your Discount Code :)" );
            helper.setFrom("braikiaymen89@gmail.com");


            String htmlContent =
                    "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; background: #ffffff; border: 1px solid #ddd; border-radius: 10px; overflow: hidden;'>"

                            + "  <div style='background: linear-gradient(90deg, #007bff, #6610f2); padding: 15px; text-align: center; color: white;'>"
                            + "    <img src='cid:logoImage' alt='EventConnect Logo' style='height: 50px; margin-bottom: 5px;'/>"
                            + "    <h2 style='margin:0;'>🎉 Congratulations!</h2>"
                            + "  </div>"

                            + "  <div style='padding: 20px;'>"
                            + "    <h3 style='color: #007bff;'>Hello " + firstNameParticipant + " " + lastNameParticipant + " 👋</h3>"
                            + "    <p>We are excited to offer you an <strong>exclusive discount</strong> for our upcoming event!</p>"
                            + "    <div style='background:#e9f7ef; padding: 15px; border-left: 5px solid #28a745; margin: 20px 0; text-align:center; border-radius:8px;'>"
                            + "       <p style='margin:0; font-size:16px;'>Your discount code is:</p>"
                            + "       <h2 style='margin:10px 0; color:#28a745; letter-spacing: 2px;'>" + discountCode + "</h2>"
                            + "       <p style='margin:0; font-size:14px; color:#555;'>Use this code during your reservation 🎟️</p>"
                            + "    </div>"
                            + "    <p style='font-size: 14px; color: #666;'>⚠️ This code is valid for one event only.</p>"
                            + "  </div>"

                            + "  <div style='background:#f1f1f1; padding: 10px; text-align: center; font-size: 12px; color:#555;'>"
                            + "    © 2025 EventConnect - All rights reserved"
                            + "  </div>"
                            + "</div>";


            helper.setText(htmlContent, true);
            FileSystemResource logo = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
            helper.addInline("logoImage", logo);


            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send discount code email", e);
        }
    }

    @Override
    public void sendFreeAccessEmail(String emailParticipant, String firstNameParticipant, String lastNameParticipant, String qrCodePath) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(emailParticipant);
            helper.setSubject("🎉 Free Access Granted!");
            helper.setFrom("braikiaymen89@gmail.com");

            String htmlContent =
                    "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; background: #ffffff; border: 1px solid #ddd; border-radius: 10px; overflow: hidden;'>"

                            // HEADER
                            + "  <div style='background: #28a745; padding: 15px; text-align: center;'>"
                            + "    <img src='cid:logoImage' alt='EventConnect Logo' style='height: 50px;'/>"
                            + "  </div>"

                            + "  <div style='padding: 20px;'>"
                            + "    <h3 style='color: #007bff;'>Hello " + firstNameParticipant + " " + lastNameParticipant + " 👋</h3>"
                            + "    <p>We are excited to offer you an <strong>Free access</strong> for our upcoming event!</p>"
                            + "    <div style='background:#e9f7ef; padding: 15px; border-left: 5px solid #28a745; margin: 20px 0; text-align:center; border-radius:8px;'>"
                            + "       <p style='margin:0; font-size:16px;'>Please present this QR code at the entrance to claim your access:</p>"
                            + "       <div style='text-align: center; margin: 20px 0;'>"
                            + "           <img src='cid:qrCodeImage' alt='QR Code'/>"
                            + "       </div>"
                            + "    </div>"
                            + "    <p style='font-size: 14px; color: #666;'>⚠️ This free access is valid for one event only.</p>"
                            + "  </div>"

                            + "  <div style='background:#f1f1f1; padding: 10px; text-align: center; font-size: 12px; color:#555;'>"
                            + "    © 2025 EventConnect - All rights reserved"
                            + "  </div>"
                            + "</div>";


            helper.setText(htmlContent, true);

            FileSystemResource qrImage = new FileSystemResource(new File(qrCodePath));
            helper.addInline("qrCodeImage", qrImage);
            FileSystemResource logo = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
            helper.addInline("logoImage", logo);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send Free Access email", e);
        }
    }



}
