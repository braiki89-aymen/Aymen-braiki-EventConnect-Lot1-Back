package tn.esprit.tic.se.spr01.Exercises.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.se.spr01.Exercises.Entities.Exercise;
import tn.esprit.tic.se.spr01.Exercises.Entities.Rating;

public interface RatingRepository  extends JpaRepository<Rating, Long> {
}
