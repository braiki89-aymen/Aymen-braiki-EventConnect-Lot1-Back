package tn.esprit.tic.se.spr01.UserManagement.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.UserManagement.DTO.CoachDto;
import tn.esprit.tic.se.spr01.UserManagement.DTO.UserDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Coach;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.CoachRepository;

@Service

@RequiredArgsConstructor
@Slf4j
public class CoachService implements iCoachService{

    @Autowired
    CoachRepository coachRepository;
    // Create
    @Override
    public Coach addCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    // Read
    @Override
    public Coach getCoachById(Long id) {
        return coachRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coach not found"));
    }

    // Update
    @Override
    public Coach updateCoach(Long id, Coach coach) {
        Coach existingCoach = coachRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coach not found"));
        existingCoach.setFirstname(coach.getFirstname());
        existingCoach.setLastname(coach.getLastname());
        existingCoach.setEmail(coach.getEmail());
        existingCoach.setPassword(coach.getPassword());
        existingCoach.setDateOfBirth(coach.getDateOfBirth());
        existingCoach.setEnabled(coach.isEnabled());
        existingCoach.setAccountLocked(coach.isAccountLocked());
        existingCoach.setspecializationCoach(coach.getSpecializationCoach());
        existingCoach.setDiplome(coach.getDiplome());
        return coachRepository.save(existingCoach);
    }

    // Delete
    @Override
    public void deleteCoach(Long id) {
        if (!coachRepository.existsById(id)) {
            throw new EntityNotFoundException("Coach not found");
        }
        coachRepository.deleteById(id);
    }
}
