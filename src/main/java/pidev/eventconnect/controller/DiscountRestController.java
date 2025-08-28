package pidev.eventconnect.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidev.eventconnect.entities.DiscountCode;
import pidev.eventconnect.services.DiscountCodeServiceImlp;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/discount")
@CrossOrigin(origins = "*")
public class DiscountRestController {

    @Autowired
    DiscountCodeServiceImlp discountCodeService ;

    @PostMapping("/checkCode")
    public ResponseEntity<Boolean> checkCode (@RequestBody String code){
        boolean exists = discountCodeService.existByCode(code);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/create")
    public ResponseEntity<DiscountCode> createDiscountCode(@RequestBody Map<String, String> body) {
        try {
            String email = body.get("emailParticipant");
            String firstName = body.get("firstNameParticipant");
            String lastName = body.get("lastNameParticipant");
            String discountCode = body.get("discountCode");

            DiscountCode code = discountCodeService.createCode(email, firstName, lastName, discountCode);
            return ResponseEntity.ok(code);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/SendFreeAccessMail")
    public void sendFreeAccess (@RequestBody Map<String, String> body) {


            String email = body.get("emailParticipant");
            String firstName = body.get("firstNameParticipant");
            String lastName = body.get("lastNameParticipant");

            discountCodeService.sendFreeAccess(email,firstName,lastName);

    }


}
