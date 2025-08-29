package pidev.eventconnect.services;

import pidev.eventconnect.dto.CancelRequest;
import pidev.eventconnect.entities.Reservation;

import java.util.List;

public interface IReservationService {

     Reservation createReservation (Reservation reservation, Long id, String discountCode);
     void cancelReservation (CancelRequest cancelRequest);

     boolean existsByEmailAndCancelCode(CancelRequest cancelRequest);

     void confirmedPendingReservation();

     List<Reservation> listConfirmedReservation (Long id);
     List<Reservation> listPendingReservation(Long id);

     int countConfirmedReservationsByEventId(Long id);
     int countPendingReservationsByEventId(Long id);
     List<Object[]> findTopParticipants();
     List<Object[]> countReservationsByAllEvents();
     double incomeEvent (Long id);
     double totalIncome ();
}
