package pidev.eventconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.eventconnect.entities.Reservation;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("SELECT r FROM Reservation r WHERE r.cancelCode = :code AND r.emailParticipant = :mail")
    Reservation findReservation(@Param("code") String code,
                                          @Param("mail") String mail);

    Optional<Reservation> findByEmailParticipantAndCancelCode(String emailParticipant, String cancelCode);

}
