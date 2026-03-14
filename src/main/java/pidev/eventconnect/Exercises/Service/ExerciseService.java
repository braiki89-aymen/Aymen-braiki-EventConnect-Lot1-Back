package tn.esprit.tic.se.spr01.Exercises.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.Exercises.Entities.Exercise;
import tn.esprit.tic.se.spr01.Exercises.Entities.Rating;
import tn.esprit.tic.se.spr01.Exercises.Repository.ExerciseRepository;

import tn.esprit.tic.se.spr01.Exercises.Repository.RatingRepository;
import tn.esprit.tic.se.spr01.TeamandPlayer.entities.Team;
import tn.esprit.tic.se.spr01.TeamandPlayer.repositories.TeamRepository;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Coach;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.UserRepository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExerciseService implements IExerciseService {
    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    TeamRepository teamRepository;

    UserRepository userRepository;
    RatingRepository ratingRepository;

    @Override
    public Exercise findById(long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No exercise found"));
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public List<User> findAllByCoach() {
        return userRepository.findAll();
    }


    @Override
    public List<Exercise> delete(long id) {
        exerciseRepository.deleteById(id);
        return findAll();
    }

    public Exercise create(Exercise exercise, int teamId, Long coachId) {
        // Fetch team
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("No team found with ID: " + teamId));
        exercise.setTeam(team);

        // Fetch coach as a User
        User coachUser = userRepository.findCoachById(coachId)
                .orElseThrow(() -> new RuntimeException("No coach found with ID: " + coachId));


        Coach coach = (Coach) coachUser;
        exercise.setCoach(coach);

        // Save exercise
        return exerciseRepository.save(exercise);
    }


    @Override
    public Exercise update(Exercise exercise, Long id) {
        Exercise existingExercise = exerciseRepository.findById(id).orElseThrow();
        existingExercise.setName(exercise.getName());
        existingExercise.setDuration(exercise.getDuration());
        existingExercise.setImage(exercise.getImage());
        existingExercise.setObjectif(exercise.getObjectif());
        existingExercise.setExerciseType(exercise.getExerciseType());
        existingExercise.setDescription(exercise.getDescription());
        existingExercise.setCreationDate(exercise.getCreationDate());
        existingExercise.setImage(exercise.getImage());
        existingExercise.setVideoUrl(exercise.getVideoUrl());


        return exerciseRepository.save(existingExercise);
    }


    public double calculateAverageRating(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercise not found with id " + exerciseId));

        List<Rating> ratings = exercise.getRatings();

        if (ratings == null || ratings.isEmpty()) {
            return 0.0;
        }

        double total = ratings.stream().mapToDouble(Rating::getRating).sum();
        return total / ratings.size();
    }


    public Exercise updateCompletionStatus(Long id, boolean completed) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        exercise.setCompleted(completed); // Mise à jour du statut
        return exerciseRepository.save(exercise);
    }
    public Rating addRatingToExercise(Long exerciseId, double ratingValue) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercise not found with id " + exerciseId));

        Rating rating = new Rating();
        rating.setRating(ratingValue);
        rating.setExercise(exercise);

        // Optional: use helper method
        exercise.addRating(rating);

        return ratingRepository.save(rating);
    }
    public Map<String, Long> getExerciseStatsByCoach(Long coachId) {
        List<Exercise> exercises = exerciseRepository.findByCoachId(coachId);

        long total = exercises.size();
        long usedInSessions = exercises.stream()
                .filter(e -> e.getSessions() != null && !e.getSessions().isEmpty())
                .count();

        long rated = exercises.stream()
                .filter(e -> e.getRatings() != null && !e.getRatings().isEmpty())
                .count();

        long completed = exercises.stream()
                .filter(Exercise::isCompleted)
                .count();

        Map<String, Long> stats = new HashMap<>();
        stats.put("totalExercises", total);
        stats.put("usedInSessions", usedInSessions);
        stats.put("ratedExercises", rated);
        stats.put("completedExercises", completed);

        return stats;
    }

}