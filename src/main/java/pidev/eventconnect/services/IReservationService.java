package pidev.eventconnect.services;

import pidev.eventconnect.dto.CancelRequest;
import pidev.eventconnect.entities.Reservation;

import java.util.List;

public interface IReservationService {

    public Reservation createReservation (Reservation reservation, Long id, String discountCode);
    public void cancelReservation (CancelRequest cancelRequest);

    public boolean existsByEmailAndCancelCode(CancelRequest cancelRequest);

    public void confirmedPendingReservation();

    public List<Reservation> listConfirmedReservation (Long id);
    public List<Reservation> listPendingReservation(Long id);

    public int countConfirmedReservationsByEventId(Long id);
    public int countPendingReservationsByEventId(Long id);
    public List<Object[]> findTopParticipants();
    public List<Object[]> countReservationsByAllEvents();
}
