package tn.esprit.tic.se.spr01.UserManagement.Services;

import tn.esprit.tic.se.spr01.UserManagement.DTO.CoachDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Coach;

public interface iCoachService {


    Coach addCoach(Coach coach);
    Coach getCoachById(Long id);
    Coach updateCoach(Long id, Coach coach);

    void deleteCoach(Long id);

}
