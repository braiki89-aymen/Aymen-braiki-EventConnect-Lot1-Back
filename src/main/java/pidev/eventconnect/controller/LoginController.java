package pidev.eventconnect.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductServiceImpl productService;

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            // Authentifier les identifiants
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Échec de l'authentification : email ou mot de passe incorrect");
        }

        try {
            // Charger l'utilisateur après validation
            User user = (User) userService.loadUserByUsername(loginRequest.getEmail());
            Long userId = user.getId();

            // Générer le JWT
            String jwt = jwtUtils.generateJwtToken(user.getUsername(), userId);

            // Créer le cookie HttpOnly contenant le JWT
            ResponseCookie cookie = ResponseCookie.from("token", jwt)
                    .httpOnly(true)
                    .secure(false) // en prod, avec HTTPS
                    .path("/")
                    .maxAge(24 * 60 * 60) // 1 jour
                    .sameSite("Strict")
                    .build();

            // Ajouter le cookie à la réponse
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            // Optionnel : tu peux retourner l’utilisateur ou juste un message
            return ResponseEntity.ok(Map.of("message", "Connexion réussie"));


        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Utilisateur non trouvé avec l'email : " + loginRequest.getEmail());
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {

        ResponseCookie cookie = ResponseCookie.from("token", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(Map.of("message", "Déconnexion réussie"));

    }









}