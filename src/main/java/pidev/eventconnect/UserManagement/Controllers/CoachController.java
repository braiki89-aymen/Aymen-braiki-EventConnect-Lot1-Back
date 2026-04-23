package tn.esprit.tic.se.spr01.UserManagement.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.UserManagement.DTO.CoachDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Coach;
import tn.esprit.tic.se.spr01.UserManagement.Services.iCoachService;

@RestController
@RequestMapping("/coach")
public class CoachController {

    final
    iCoachService coachService;

    public CoachController(iCoachService coachService) {
        this.coachService = coachService;
    }


    @PostMapping("/addCoach")
    public ResponseEntity<Coach> addCoach(@RequestBody Coach coach) {
        return ResponseEntity.ok(coachService.addCoach(coach));
    }

    @GetMapping("/getCoach/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Long id) {
        return ResponseEntity.ok(coachService.getCoachById(id));
    }

    @PutMapping("/updateCoach/{id}")
    public ResponseEntity<Coach> updateCoach(@PathVariable Long id, @RequestBody Coach coach) {
        return ResponseEntity.ok(coachService.updateCoach(id, coach));
    }

    @DeleteMapping("/deleteCoach/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Long id) {
        coachService.deleteCoach(id);
        return ResponseEntity.noContent().build();
    }

}
