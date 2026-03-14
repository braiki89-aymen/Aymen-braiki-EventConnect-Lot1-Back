package tn.esprit.tic.se.spr01.TacticalBoard.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsMonthlyActivity;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsPlayerCount;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsBoard;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TacticsBoardRepository extends JpaRepository<TacticsBoard, Long> {

    List<TacticsBoard> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);
    @Query("SELECT t FROM TacticsBoard t WHERE SIZE(t.players) = 0")
    List<TacticsBoard> findEmptyBoards();

    Page<TacticsBoard> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Requête pour compter les tableaux tactiques avec une version supérieure à la valeur spécifiée
    long countByVersionGreaterThan(Long version);

    // Requête pour obtenir les données d'activité mensuelle
    @Query("SELECT new tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsMonthlyActivity(CAST(FUNCTION('DATE_FORMAT', t.createdAt, '%M')AS string), COUNT(t)) " +
            "FROM TacticsBoard t " +
            "WHERE t.createdAt IS NOT NULL " +
            "GROUP BY FUNCTION('DATE_FORMAT', t.createdAt, '%M'), MONTH(t.createdAt) " +
            "ORDER BY MONTH(t.createdAt)")
    List<TacticsMonthlyActivity> getMonthlyActivityData();

    // Requête pour obtenir le nombre de joueurs par tableau tactique
    @Query("SELECT new tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsPlayerCount(t.name, SIZE(t.players)) " +
            "FROM TacticsBoard t " +
            "WHERE SIZE(t.players) > 0 AND t.name IS NOT NULL " +
            "ORDER BY SIZE(t.players) DESC")
    List<TacticsPlayerCount> getPlayerCountByBoard();
}