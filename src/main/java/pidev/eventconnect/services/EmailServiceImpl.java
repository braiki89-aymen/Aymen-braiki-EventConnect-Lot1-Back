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
                    "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #f8f9fa;'>" +
                            "<h2 style='color: #28a745;'>Your reservation is confirmed!</h2>" +
                            "<p>Hello <strong>" + reservation.getFirstNameParticipant() + " " + reservation.getLastNameParticipant() + "</strong>,</p>" +
                            "<p>You have successfully reserved <strong>" + reservation.getNbPlace() + "</strong> place(s) for the event:</p>" +
                            "<p><strong>" + reservation.getEvent().getTitle() + "</strong></p>" +
                            "<p><strong>Cancellation Code:</strong> " + reservation.getCancelCode() + "</p>" +
                            "<p>Please present this QR code at the entrance:</p>" +
                            "<div style='text-align: center; margin: 20px 0;'>" +
                            "   <img src='cid:qrCodeImage' alt='QR Code'/>" +
                            "</div>" +
                            "<p style='font-size: 14px; color: #666;'>If you need to cancel, use your cancellation code.</p>" +
                            "</div>";

            helper.setText(htmlContent, true);


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
                    "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #fff3cd;'>" +
                            "<h2 style='color: #856404;'>You are on the waiting list</h2>" +
                            "<p>Hello <strong>" + reservation.getFirstNameParticipant() + " " + reservation.getLastNameParticipant() + "</strong>,</p>" +
                            "<p>The event <strong>" + reservation.getEvent().getTitle() + "</strong> is currently full.</p>" +
                            "<p>You have been placed on the <strong>waiting list</strong>. We will notify you if a spot becomes available.</p>" +
                            "<p style='font-size: 14px; color: #666;'>Thank you for your patience.</p>" +
                            "</div>";

            helper.setText(htmlContent, true);
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
                    "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #f8f9fa;'>" +
                            "<h2 style='color: #007bff;'>Hello " + firstNameParticipant + " " + lastNameParticipant + "!</h2>" +
                            "<p>We are happy to offer you a discount </p>" +

                            "<p>Your discount code is:</p>" +
                            "<h3 style='color: #28a745;'>" + discountCode + "</h3>" +
                            "<p>Use this code during your reservation to enjoy your discount.</p>" +
                            "<p style='font-size: 14px; color: #666;'>This code is valid for a just one Event.</p>" +
                            "</div>";

            helper.setText(htmlContent, true);

            // Envoi
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
                    "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #f8f9fa;'>" +
                            "<h2 style='color: #007bff;'>Hello " + firstNameParticipant + " " + lastNameParticipant + "!</h2>" +
                            "<p>We are excited to offer you <strong>Free Access</strong> to our upcoming event!</p>" +
                            "<p>Please present this QR code at the entrance to claim your access:</p>" +
                            "<div style='text-align: center; margin: 20px 0;'>" +
                            "   <img src='cid:qrCodeImage' alt='QR Code'/>" +
                            "</div>" +
                            "<p style='font-size: 14px; color: #666;'>This free access is valid for one event only.</p>" +
                            "</div>";

            helper.setText(htmlContent, true);

            FileSystemResource qrImage = new FileSystemResource(new File(qrCodePath));
            helper.addInline("qrCodeImage", qrImage);

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send Free Access email", e);
        }
    }



}
