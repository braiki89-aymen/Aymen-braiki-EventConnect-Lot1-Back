package tn.esprit.tic.se.spr01.Exercises.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.Exercises.Entities.Exercise;
import tn.esprit.tic.se.spr01.Exercises.Entities.Rating;
import tn.esprit.tic.se.spr01.Exercises.Service.ExerciseService;
import tn.esprit.tic.se.spr01.Exercises.Service.IExerciseService;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/exercice")
@CrossOrigin(origins = "http://localhost:4200")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @PostMapping("/create/{teamId}/{coachid}")
    public ResponseEntity<?> create(
            @RequestBody Exercise exercise,
            @PathVariable int teamId,
            @PathVariable Long coachid) {
        try {
            Exercise createdExercise = exerciseService.create(exercise, teamId, coachid);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("findBy/{id}")
    public Exercise findById(@PathVariable("id") Long id) {
        return exerciseService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Exercise> findAll() {
        return exerciseService.findAll();
    }

    @PutMapping("/update/{id}")
    public Exercise update(@RequestBody Exercise exercise, @PathVariable("id") Long id) {
        return exerciseService.update(exercise, id);
    }

    @DeleteMapping("/delete/{id}")
    public List<Exercise> delete(@PathVariable("id") Long id) {
        return exerciseService.delete(id);
    }

    @GetMapping("/{exerciseId}/average-rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long exerciseId) {
        double average = exerciseService.calculateAverageRating(exerciseId);
        return ResponseEntity.ok(average);
    }
    @PostMapping("/{exerciseId}/ratings")
    public ResponseEntity<Rating> addRating(@PathVariable Long exerciseId, @RequestParam double rating) {
        Rating newRating = exerciseService.addRatingToExercise(exerciseId, rating);
        return ResponseEntity.ok(newRating);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Exercise> markExerciseAsCompleted(
            @PathVariable Long id,
            @RequestBody boolean completed) {
        Exercise updatedExercise = exerciseService.updateCompletionStatus(id, completed);
        return ResponseEntity.ok(updatedExercise);
    }

    @GetMapping("/coach/{coachId}/stats")
    public ResponseEntity<Map<String, Long>> getCoachExerciseStats(@PathVariable Long coachId) {
        Map<String, Long> stats = exerciseService.getExerciseStatsByCoach(coachId);
        return ResponseEntity.ok(stats);
    }



}