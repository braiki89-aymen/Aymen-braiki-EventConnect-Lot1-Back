package tn.esprit.tic.se.spr01.UserManagement.Services.JWT;

import tn.esprit.tic.se.spr01.UserManagement.DTO.SignupRequest;

public interface AuthService {
    boolean createUtilisateur(SignupRequest signupRequest);

    void sendEmail(String email, String subject, String message);
}
