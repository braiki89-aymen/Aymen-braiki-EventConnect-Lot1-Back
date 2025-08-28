package pidev.eventconnect.services;

import pidev.eventconnect.entities.Reservation;

public interface IEmailService {

    public void sendEmail(String to, String subject, String body);

    public void sendConfirmationEmail(Reservation reservation);
    public void sendWaitingEmail(Reservation reservation);
    public void sendDiscountCodeEmail(String emailParticipant, String firstNameParticipant, String lastNameParticipant,
                                      String discountCode);
    public void sendFreeAccessEmail(String emailParticipant, String firstNameParticipant, String lastNameParticipant,
                                    String qrCodePath);

}
