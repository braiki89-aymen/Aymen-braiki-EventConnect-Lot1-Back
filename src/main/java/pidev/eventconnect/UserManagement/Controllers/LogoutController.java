package tn.esprit.tic.se.spr01.UserManagement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.UserManagement.DTO.UserDto;
import tn.esprit.tic.se.spr01.UserManagement.Services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint pour déconnecter un utilisateur
     * @param username le nom d'utilisateur à déconnecter
     * @return ResponseEntity avec le statut de la déconnexion
     */
    @PostMapping("/{username}")
    public ResponseEntity<?> logout(@PathVariable String username) {
        UserDto userDto = userService.logout(username);
        if (userDto != null) {
            return ResponseEntity.ok().body("Déconnexion réussie");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Utilisateur non trouvé avec le nom : " + username);
        }
    }
}
