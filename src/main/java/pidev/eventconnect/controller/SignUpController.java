package pidev.eventconnect.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidev.eventconnect.login.services.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/signup")

public class SignUpController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping
    @ResponseBody

    public ResponseEntity<Map<String, String>> signup(@RequestBody SignupRequest signupRequest) {
        Map<String, String> response = new HashMap<>();
        boolean isUserCreated = userService.createUser(signupRequest);

        if (isUserCreated) {
            response.put("message", "User added successfully");



            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.put("message", "Failed to create user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
