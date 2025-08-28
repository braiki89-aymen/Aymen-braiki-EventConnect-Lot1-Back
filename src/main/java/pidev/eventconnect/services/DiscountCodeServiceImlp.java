package pidev.eventconnect.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.eventconnect.entities.DiscountCode;
import pidev.eventconnect.repository.DiscountCodeRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DiscountCodeServiceImlp implements IDiscountCodeService{
    @Autowired
    DiscountCodeRepository discountCodeRepository;
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    ReservationServiceImpl reservationService;

    @Override
    public boolean existByCode(String code) {
        return discountCodeRepository.findByCode(code).isPresent();
    }

    @Override
    public DiscountCode createCode(String emailParticipant, String firstNameParticipant, String lastNameParticipant, String discountCode) {
        String discount = UUID.randomUUID().toString();
        DiscountCode code = new DiscountCode();
        code.setCode(discount);
        discountCodeRepository.save(code);
        emailService.sendDiscountCodeEmail(emailParticipant,firstNameParticipant,lastNameParticipant,discount);


        return null;
    }

    @Override
    public void sendFreeAccess(String emailParticipant, String firstNameParticipant, String lastNameParticipant) {

        String qrContent = "Name: " + firstNameParticipant + " " + lastNameParticipant;

        String qrPath = "qrcodes/free-access-" + firstNameParticipant + "-" + lastNameParticipant + ".png";
        try {

            reservationService.generateQRCode(qrContent, qrPath);
        } catch (Exception e) {
            throw new RuntimeException("Error generating QR Code", e);
        }


        emailService.sendFreeAccessEmail(emailParticipant, firstNameParticipant, lastNameParticipant, qrPath);
    }


}
