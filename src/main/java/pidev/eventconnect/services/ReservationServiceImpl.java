package pidev.eventconnect.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pidev.eventconnect.dto.CancelRequest;
import pidev.eventconnect.entities.Event;
import pidev.eventconnect.entities.Reservation;
import pidev.eventconnect.entities.Status;
import pidev.eventconnect.repository.EventRepository;
import pidev.eventconnect.repository.ReservationRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService{
    @Autowired
    EventRepository eventRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    EmailServiceImpl emailService;
    @Override
    public Reservation createReservation(Reservation reservation, Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + id));

        // Initialiser la réservation
        Reservation reservation1 = new Reservation();
        reservation1.setEmailParticipant(reservation.getEmailParticipant());
        reservation1.setFirstNameParticipant(reservation.getFirstNameParticipant());
        reservation1.setLastNameParticipant(reservation.getLastNameParticipant());
        reservation1.setNbPlace(reservation.getNbPlace());
        reservation1.setEvent(event);

        // Vérifier la capacité et définir le statut
        if (event.getNbParticipantsActuels() == null) {
            event.setNbParticipantsActuels(0L); // sécurité null
        }

        if (event.getNbParticipantsActuels() < event.getCapacityMax()) {
            reservation1.setStatus(Status.CONFIRMED);
            reservation1.setCancelCode(UUID.randomUUID().toString());
            event.setNbParticipantsActuels(event.getNbParticipantsActuels() + 1);
        } else {
            reservation1.setStatus(Status.PENDING);
        }

        // Sauvegarder d'abord pour avoir un ID
        reservation1 = reservationRepository.save(reservation1);

        // Générer le QR code seulement si confirmé
        if (reservation1.getStatus() == Status.CONFIRMED) {
            String qrContent = "Reservation ID: " + reservation1.getId() +
                    "\nName: " + reservation1.getFirstNameParticipant() + " " + reservation1.getLastNameParticipant() +
                    "\nEvent: " + reservation1.getEvent().getTitle() +
                    "\nCancel Code: " + reservation1.getCancelCode();

            String qrPath = "qrcodes/reservation-" + reservation1.getId() + ".png";

            try {
                generateQRCode(qrContent, qrPath);
                reservation1.setQrCodeBase64(qrPath);
                reservation1 = reservationRepository.save(reservation1);
            } catch (Exception e) {
                throw new RuntimeException("Error generating QR Code", e);
            }

            // Envoi du mail après génération du QR code
            emailService.sendConfirmationEmail(reservation1);
        } else {
            // Email pour liste d'attente
            emailService.sendWaitingEmail(reservation1);
        }

        return reservation1;
    }

    @Override
    public void cancelReservation(CancelRequest cancelRequest) {
        Reservation reservation = reservationRepository.findReservation(cancelRequest.getCancelCode(),
                cancelRequest.getEmailParticipant());
        if (reservation == null) {
            throw new EntityNotFoundException("Reservation not found with email: "
                    + cancelRequest.getEmailParticipant() + " and code: " + cancelRequest.getCancelCode());
        }

        Long id = reservation.getEvent().getId();
        Event event = eventRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + id));
        event.setNbParticipantsActuels(event.getNbParticipantsActuels() - 1);
        eventRepository.save(event);

        reservationRepository.delete(reservation);
    }

    @Override
    public boolean existsByEmailAndCancelCode(CancelRequest cancelRequest) {
       return reservationRepository.findByEmailParticipantAndCancelCode(cancelRequest.getEmailParticipant(),
               cancelRequest.getCancelCode()).isPresent();
    }

    @Override
    @Scheduled(fixedDelay = 300000)
    public void confirmedPendingReservation(){
        List<Reservation> PendingReservations = reservationRepository.findPendingReservations();
        if (PendingReservations == null) {
            throw new EntityNotFoundException("Pending List empty ");

        }
        for (Reservation re : PendingReservations){
           Event event = re.getEvent();
           if(event.getNbParticipantsActuels() < event.getCapacityMax()){
               re.setStatus(Status.CONFIRMED);
               re.setCancelCode(UUID.randomUUID().toString());
               event.setNbParticipantsActuels(event.getNbParticipantsActuels() + 1);
               reservationRepository.save(re);
               eventRepository.save(event);
               String qrContent = "Reservation ID: " + re.getId() +
                       "\nName: " + re.getFirstNameParticipant() + " " + re.getLastNameParticipant() +
                       "\nEvent: " + re.getEvent().getTitle() +
                       "\nCancel Code: " + re.getCancelCode();
               String qrPath = "qrcodes/reservation-" + re.getId() + ".png";
               try {
                   generateQRCode(qrContent, qrPath);
                   re.setQrCodeBase64(qrPath);
                   re = reservationRepository.save(re);
               } catch (Exception e) {
                   throw new RuntimeException("Error generating QR Code", e);
               }
               emailService.sendConfirmationEmail(re);

           }
        }

    }

    @Override
    public List<Reservation> listConfirmedReservation(Long id) {
        return reservationRepository.findConfirmedReservations(id);
    }

    @Override
    public List<Reservation> listPendingReservation(Long id) {
        return reservationRepository.findPendingReservations1(id);
    }

    @Override
    public int countConfirmedReservationsByEventId(Long id) {
        return reservationRepository.countConfirmedReservationsByEventId(id);

    }

    @Override
    public int countPendingReservationsByEventId(Long id) {
        return  reservationRepository.countPendingReservationsByEventId(id);

    }

    @Override
    public List<Object[]> findTopParticipants() {
        return reservationRepository.findTopParticipants();
    }

    @Override
    public List<Object[]> countReservationsByAllEvents() {
        return reservationRepository.countReservationsByAllEvents();
    }

    private void generateQRCode(String text, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        var bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 250, 250);

        File file = new File(filePath);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", fos);
        }
    }

}
