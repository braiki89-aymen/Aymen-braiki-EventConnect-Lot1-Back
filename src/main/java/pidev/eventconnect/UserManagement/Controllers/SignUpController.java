package tn.esprit.tic.se.spr01.UserManagement.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.UserManagement.DTO.SignupRequest;
import tn.esprit.tic.se.spr01.UserManagement.Entities.*;
import tn.esprit.tic.se.spr01.UserManagement.Services.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = "http://localhost:4200")
public class SignUpController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint pour inscrire un nouvel utilisateur
     * @param signupRequest les informations d'inscription
     * @return ResponseEntity avec les données utilisateur et un message de succès
     */
    @PostMapping
    public ResponseEntity<?> register(@RequestBody SignupRequest signupRequest) {
        try {
            // Vérifier si l'email existe déjà
            Optional<User> existingUser = userService.getUserByEmail(signupRequest.getEmail());
            if (existingUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Un utilisateur avec cet email existe déjà");
            }

            // Créer un nouvel utilisateur
            User newUser = new User();
            newUser.setFirstname(signupRequest.getFirstname());
            newUser.setLastname(signupRequest.getLastname());
            newUser.setEmail(signupRequest.getEmail());
            newUser.setPassword(signupRequest.getPassword());
            newUser.setAvatarUrl(signupRequest.getAvatarUrl());
            newUser.setStatus(Status.OFFLINE);
            newUser.setEnabled(true);
            newUser.setAccountLocked(false);
            
            // Définir le rôle de l'utilisateur
            UserRole role = UserRole.valueOf(signupRequest.getRoleUser().toString());
            newUser.setRoleUser(role);
            
            // Ajouter les champs spécifiques au rôle
            switch (role) {
                case ADMIN:
                    Admin admin = new Admin();
                    admin.setDepartment(signupRequest.getDepartment());
                    admin.setAdminLevel(signupRequest.getAdminLevel());
                    break;
                case COACH:
                    Coach coach = new Coach();
                    coach.setspecializationCoach(signupRequest.getSpecializationCoach());
                    coach.setDiplome(signupRequest.getDiplome());

                    break;
                case MEDICALSTAFF:
                    MedicalStaff medicalStaff = new MedicalStaff();
                    medicalStaff.setSpecializationMed(signupRequest.getSpecializationMed());
                    medicalStaff.setLicenseNumber(signupRequest.getLicenseNumber());
                    break;
                case PLAYER:
                    Player player = new Player();
                    player.setRole(signupRequest.getRole());
                    player.setJerseyNumber(signupRequest.getJerseyNumber());
                    break;
            }
            
            // Sauvegarder l'utilisateur
            User savedUser = userService.addUser(newUser);
            
            // Créer la réponse
            Map<String, Object> response = new HashMap<>();
            response.put("user", savedUser);
            response.put("message", "Inscription réussie");
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'inscription : " + e.getMessage());
        }
    }
}

