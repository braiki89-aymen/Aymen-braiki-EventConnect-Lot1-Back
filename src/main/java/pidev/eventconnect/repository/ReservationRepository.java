package pidev.eventconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.eventconnect.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
