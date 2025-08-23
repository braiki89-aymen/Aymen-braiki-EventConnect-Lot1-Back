package pidev.eventconnect.services;

import pidev.eventconnect.dto.CancelRequest;
import pidev.eventconnect.entities.Reservation;

public interface IReservationService {

    public Reservation createReservation (Reservation reservation, Long id);
    public void cancelReservation (CancelRequest cancelRequest);

    public boolean existsByEmailAndCancelCode(CancelRequest cancelRequest);
}
