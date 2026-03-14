package tn.esprit.tic.se.spr01.TacticalBoard.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsStats;

public interface TacticsStatsRepository extends JpaRepository<TacticsStats, Long> {
}