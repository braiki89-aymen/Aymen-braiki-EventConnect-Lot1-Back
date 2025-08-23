package pidev.eventconnect.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidev.eventconnect.dto.StripeRequest;
import pidev.eventconnect.dto.StripeResponse;
import pidev.eventconnect.services.StripeService;

@RestController
@AllArgsConstructor
@RequestMapping("/paiement")
@CrossOrigin(origins = "*")
public class CheckoutRestController {

    @Autowired
    StripeService stripeService;

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody StripeRequest stripeRequest) {
        StripeResponse stripeResponse = stripeService.checkoutProducts(stripeRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }
}
