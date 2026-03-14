package tn.esprit.tic.se.spr01.TacticalBoard.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsAnimation;

public interface TacticsAnimationRepository extends JpaRepository<TacticsAnimation, Long> {
}