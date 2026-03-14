package tn.esprit.tic.se.spr01.Exercises.Service;

import tn.esprit.tic.se.spr01.Exercises.Entities.Exercise;



import java.util.List;

public interface IExerciseService {
    Exercise findById(long id);
    List<Exercise> findAll();
    List<Exercise> delete(long id);
    Exercise update(Exercise exercise,Long id);
    Exercise create(Exercise exercise, int teamId, Long coach) ;




    }
