package tn.esprit.tic.se.spr01.TacticalBoard.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.PlayerIcon;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsBoard;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Player;

import java.util.List;

public interface PlayerIconRepository extends JpaRepository<PlayerIcon, Long> {

    List<PlayerIcon> findByTacticsBoard(TacticsBoard tacticsBoard);

    PlayerIcon findByJerseyNumber(Integer jerseyNumber);

}