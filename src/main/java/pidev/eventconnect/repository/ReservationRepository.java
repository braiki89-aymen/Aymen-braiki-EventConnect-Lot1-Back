package pidev.eventconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.eventconnect.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("SELECT r FROM Reservation r WHERE r.cancelCode = :code AND r.emailParticipant = :mail")
    Reservation findReservation(@Param("code") String code,
                                          @Param("mail") String mail);

    Optional<Reservation> findByEmailParticipantAndCancelCode(String emailParticipant, String cancelCode);
    @Query("SELECT r FROM Reservation r WHERE r.status = 'CONFIRMED' AND r.event.id = :id")
    List<Reservation> findConfirmedReservations(@Param("id") Long id);
    @Query("SELECT r FROM Reservation r WHERE r.status = 'PENDING' AND r.event.id = :id  ")
    List<Reservation> findPendingReservations1(@Param("id") Long id);

    @Query("SELECT r FROM Reservation r WHERE r.status = 'PENDING'")
    List<Reservation> findPendingReservations();

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.event.id = :id AND r.status = 'CONFIRMED'")
    int countConfirmedReservationsByEventId(@Param("id") Long id);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.event.id = :id AND r.status = 'PENDING' ")
    int countPendingReservationsByEventId(@Param("id") Long id);

    @Query("SELECT r.emailParticipant,r.firstNameParticipant,r.lastNameParticipant, COUNT(r) as total FROM Reservation r GROUP BY r.emailParticipant ORDER BY total DESC")
    List<Object[]> findTopParticipants();

    @Query("SELECT r.event.title, COUNT(r) as total FROM Reservation r GROUP BY r.event.id")
    List<Object[]> countReservationsByAllEvents();

    @Query("select sum(r.amount) from Reservation r where r.event.id = :id")
    public double incomeByEvent(@Param("id") Long id);

    @Query("select sum(r.amount) from Reservation r ")
     public double totalIncome ();





}
