package tn.esprit.tic.se.spr01.Exercises.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.tic.se.spr01.Exercises.Entities.Exercise;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Coach;

import java.util.List;
import java.util.Optional;

public interface  ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByCoach(Coach coach);

    List<Exercise> findByCoachId(Long coachId);
}


