package pidev.eventconnect.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidev.eventconnect.dto.CancelRequest;
import pidev.eventconnect.entities.Reservation;
import pidev.eventconnect.services.ReservationServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
@CrossOrigin(origins = "*")
public class ReservationRestController {
    @Autowired
    ReservationServiceImpl reservationService;


     @PostMapping("/addReservation/{id}")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation,
                                            @PathVariable ("id")Long id){
        try {
            Reservation reservation1 = reservationService.createReservation(reservation,id);
            return ResponseEntity.ok(reservation1);
        } catch (Exception e) {
            e.printStackTrace();
            // renvoyer le message de l'exception dans le body
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    @PostMapping("/cancelReservation")
    public void cancelReservation (@RequestBody CancelRequest cancelRequest){
         reservationService.cancelReservation(cancelRequest);
    }

    @PostMapping("/checkReservation")
    public ResponseEntity<Boolean> checkReservation(@RequestBody CancelRequest request) {
        boolean exists = reservationService.existsByEmailAndCancelCode(request);

        return ResponseEntity.ok(exists);
    }

    @GetMapping("/confirmed/{id}")
    public List<Reservation> listConfirmedReservation (@PathVariable("id") Long id){
         return reservationService.listConfirmedReservation(id);
    }

    @GetMapping("/pending/{id}")
    public List<Reservation> listPendingReservation (@PathVariable ("id")Long id){
         return reservationService.listPendingReservation(id);
    }

}
