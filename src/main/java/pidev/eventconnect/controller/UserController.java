package pidev.eventconnect.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidev.eventconnect.login.Repository.UserRepository;
import pidev.eventconnect.login.entities.User;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/userConnected")
    public ResponseEntity<User> getUserConnected(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(user);
    }

}

