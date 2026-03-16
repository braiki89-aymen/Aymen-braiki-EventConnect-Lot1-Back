package tn.esprit.tic.se.spr01.UserManagement.Services.JWT;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.UserManagement.DTO.SignupRequest;
import tn.esprit.tic.se.spr01.UserManagement.Entities.*;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.UserRepository;

import static tn.esprit.tic.se.spr01.UserManagement.Entities.UserRole.*;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImp implements AuthService {
    @Autowired
    UserRepository utilisateurRepository;

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public boolean createUtilisateur(SignupRequest signupRequest) {
        if (utilisateurRepository.existsByEmail(signupRequest.getEmail())) {
            System.out.println("Utilisateur avec email " + signupRequest.getEmail() + " existe déjà");

            return false;
        }

        User utilisateur;

        switch (signupRequest.getRoleUser()) {
            case ADMIN -> {
                Admin admin = new Admin();
                admin.setDepartment(signupRequest.getDepartment());
                admin.setAdminLevel(signupRequest.getAdminLevel());
                utilisateur = admin;
            }
            case COACH -> {
                Coach coach = new Coach();
                coach.setspecializationCoach(signupRequest.getSpecializationCoach());
                coach.setDiplome(signupRequest.getDiplome());
                utilisateur = coach;
            }
            case MEDICALSTAFF -> {
                MedicalStaff medicalStaff = new MedicalStaff();
                medicalStaff.setSpecializationMed(signupRequest.getSpecializationMed());
                medicalStaff.setLicenseNumber(signupRequest.getLicenseNumber());
                utilisateur = medicalStaff;
            }
            case PLAYER -> {
                Player player = new Player();
                player.setRole(signupRequest.getRole());
                player.setJerseyNumber(signupRequest.getJerseyNumber());
                utilisateur = player;
            }
            default -> {
                System.out.println("Rôle invalide : {}" + signupRequest.getRole()) ;

                return false;
            }
        }

        utilisateur.setFirstname(signupRequest.getFirstname());
        utilisateur.setLastname(signupRequest.getLastname());
        utilisateur.setEmail(signupRequest.getEmail());
        utilisateur.setPassword(signupRequest.getPassword());
        utilisateur.setRoleUser(signupRequest.getRoleUser());

        utilisateurRepository.save(utilisateur);
        System.out.println("Utilisateur {} créé avec succès" + signupRequest.getEmail());

        return true;
    }


    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("ton.email@gmail.com");

        mailSender.send(message);
    }
}
