package tn.esprit.tic.se.spr01.UserManagement.Controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tic.se.spr01.UserManagement.DTO.UserDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Status;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Services.iUserService;

import java.time.LocalDate;
import java.util.List;

@RestController

@RequestMapping("/api/v1/users")
public class UserController {

    final
    iUserService userService;

    public UserController(iUserService userService) {
        this.userService = userService;
    }


    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping
    public ResponseEntity<UserDto> login(@RequestBody final UserDto userDTO) {
        return ResponseEntity.ok(userService.login(userDTO));
    }


    @MessageMapping("/user/connect") // Receives message from clients sending to /app/user/connect
    @SendTo("/topic/active")// Broadcasts the response to all clients subscribed to /topic/active
    public UserDto connect(@Payload UserDto userDTO) {
        userDTO = userService.connect(userDTO);
        return userDTO;
    }


    @MessageMapping("/user/disconnect")
    @SendTo("/topic/active")
    public UserDto disconnect(@Payload final UserDto userDTO) {
        return userService.logout(userDTO.getEmail());
    }


    @GetMapping("/online")
    public ResponseEntity<List<UserDto>> getOnlineUsers() {
        return ResponseEntity.ok(userService.getAllOnlineUsers());
    }


    @GetMapping("search/{firstname}")
    public ResponseEntity<List<UserDto>> searchUsers(@PathVariable final String firstname) {
        return ResponseEntity.ok(userService.searchUsers(firstname));
    }


//    @PostMapping("/search-with-exclusion/{firstname}")
//    public ResponseEntity<List<UserDto>> searchUsersWithExclusion(@PathVariable final String firstname,
//                                                                  @RequestBody final List<UserDto> excludedUsers) {
//        return ResponseEntity.ok(userService.searchUsersWithExclusion(firstname, excludedUsers));
//    }

    @PostMapping("/search-with-exclusion/{firstname}")
    public ResponseEntity<List<UserDto>> searchUsersWithExclusion(
            @PathVariable String firstname,
            @RequestBody List<Long> excludedIds     // ← on reçoit juste une liste d’IDs
    ) {
        return ResponseEntity.ok(
                userService.searchUsersWithExclusion(firstname, excludedIds)
        );
    }


    @GetMapping("/status/{firstname}")
    public ResponseEntity<Status> getUserStatus(@PathVariable final String email) {
        return ResponseEntity.ok(userService.getUserStatus(email));
    }


    @PostMapping("/avatar")
    public ResponseEntity<UserDto> updateAvatar(@RequestParam("email") String email,
                                                @RequestParam("avatar") MultipartFile avatar) {
        return ResponseEntity.ok(userService.updateAvatar(email, avatar));
    }




}