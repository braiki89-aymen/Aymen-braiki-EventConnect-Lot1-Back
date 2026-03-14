package tn.esprit.tic.se.spr01.TacticalBoard.Services;

import org.springframework.data.domain.Page;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsBoardDto;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsEngagementData;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsMonthlyActivity;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsPlayerCount;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.PlayerIcon;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.Position;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsBoard;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Player;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface iTacticsBoardService {

    TacticsBoard addBoard(TacticsBoardDto dto);
    TacticsBoard getBoardById(Long id);
//    List<TacticsBoard> getAllBoard();
    Page<TacticsBoard> getAllBoardsPaginated(int page, int size, String search, String sort);
    List<TacticsBoard> getBoardByDateRange(LocalDate startDate, LocalDate endDate);
    TacticsBoard updateBoard(Long id, TacticsBoardDto dto);
    void deleteBoard(Long id);

    PlayerIcon addPlayer(PlayerIcon player, Long tacticsBoardId);
//    void updateFormation(Long tacticsBoardId, Map<String, Position> formation);

//    void setAwayTeam(Long tacticsBoardId, Long teamId);
//
//    void setHomeTeam(Long tacticsBoardId, Long teamId);

    void addPlayerToTacticsBoard(Long tacticsBoardId, Long playerId);

    List<PlayerIcon> getPlayersByTacticsBoard(Long tacticsBoardId);
    PlayerIcon updatePlayer(PlayerIcon player, Long tacticsBoardId);

    void deletePlayer(Integer jerseyNumber, Long tacticsBoardId);

    // Méthodes pour les statistiques
    TacticsEngagementData getEngagementData();
    List<TacticsMonthlyActivity> getMonthlyActivityData();
    List<TacticsPlayerCount> getPlayerCountByBoard();

}
