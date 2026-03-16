package tn.esprit.tic.se.spr01.UserManagement.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.UserManagement.DTO.LoginRequest;
import tn.esprit.tic.se.spr01.UserManagement.DTO.UserDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Status;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Services.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint pour authentifier un utilisateur
     * @param loginRequest les informations de connexion (email et mot de passe)
     * @return ResponseEntity avec les données utilisateur et un message de succès
     */
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Rechercher l'utilisateur par email
            Optional<User> userOptional = userService.getUserByEmail(loginRequest.getEmail());
            
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                
                try {
                    // Vérifier le mot de passe en utilisant la méthode de validation du service
                    userService.validatePassword(user, loginRequest.getPassword());
                    // Mettre à jour le statut et la dernière connexion
                    user.setStatus(Status.ONLINE);
                    user.setLastLogin(LocalDateTime.now());
                    userService.updateUser(user.getId(), user);
                    
                    // Créer un DTO pour éviter les problèmes de sérialisation JSON
                    UserDto userDto = new UserDto();
                    userDto.setFirstname(user.getFirstname());
                    userDto.setEmail(user.getEmail());
                    userDto.setStatus(user.getStatus());
                    userDto.setAvatarUrl(user.getAvatarUrl());
                    
                    // Créer la réponse
                    Map<String, Object> response = new HashMap<>();
                    response.put("user", userDto);
                    response.put("message", "Connexion réussie");
                    
                    return ResponseEntity.ok(response);
                } catch (IllegalArgumentException e) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body("Mot de passe incorrect");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Utilisateur non trouvé avec l'email : " + loginRequest.getEmail());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la connexion : " + e.getMessage());
        }
    }
}
