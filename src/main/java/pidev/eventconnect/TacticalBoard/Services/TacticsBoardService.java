package tn.esprit.tic.se.spr01.TacticalBoard.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsBoardDto;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsEngagementData;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsMonthlyActivity;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsPlayerCount;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.PlayerIcon;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.Position;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsBoard;
import tn.esprit.tic.se.spr01.TacticalBoard.Repositories.PlayerIconRepository;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Player;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.CoachRepository;
import tn.esprit.tic.se.spr01.TacticalBoard.Repositories.TacticsBoardRepository;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Coach;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.PlayerRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TacticsBoardService implements iTacticsBoardService{
//    @Autowired
//    private TeamRepository teamRepository;

    @Autowired
    PlayerIconRepository playerRepository;

    @Autowired
    TacticsBoardRepository tacticsBoardRepository;

    @Autowired
    CoachRepository coachRepository;

    @Override
    public TacticsBoard addBoard(TacticsBoardDto dto) {
//        Coach coach = coachRepository.findById(dto.coachId())
//                .orElseThrow(() -> new EntityNotFoundException("Coach not found with id: " + dto.coachId()));

        TacticsBoard board = new TacticsBoard();
        board.setName(dto.name());

        // Set createdAt to current date if not provided
        board.setCreatedAt(dto.createdAt() != null ? dto.createdAt() : LocalDate.now());
//        board.setCoach(coach);

        return tacticsBoardRepository.save(board);
    }

    @Override
    public TacticsBoard getBoardById(Long id) {
        return tacticsBoardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TacticsBoard not found with id: " + id));
    }

    public Page<TacticsBoard> getAllBoardsPaginated(int page, int size, String search, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        if ("asc".equalsIgnoreCase(sort)) {
            pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
        }

        if (search != null && !search.isEmpty()) {
            return tacticsBoardRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            return tacticsBoardRepository.findAll(pageable);
        }
    }


    @Override
    public List<TacticsBoard> getBoardByDateRange(LocalDate startDate, LocalDate endDate) {
        return tacticsBoardRepository.findByCreatedAtBetween(startDate, endDate);
    }

    @Override
    public TacticsBoard updateBoard(Long id, TacticsBoardDto dto) {
        TacticsBoard existingBoard = tacticsBoardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TacticsBoard not found with id: " + id));

//        Coach coach = coachRepository.findById(dto.coachId())
//                .orElseThrow(() -> new EntityNotFoundException("Coach not found with id: " + dto.coachId()));

        existingBoard.setName(dto.name());

        existingBoard.setCreatedAt(dto.createdAt());
//        existingBoard.setCoach(coach);


        return tacticsBoardRepository.save(existingBoard);
    }
    @Override
    public void deleteBoard(Long id) {
        if (!tacticsBoardRepository.existsById(id)) {
            throw new EntityNotFoundException("TacticsBoard not found with id: " + id);
        }
        tacticsBoardRepository.deleteById(id);
    }


    @Override
    public void addPlayerToTacticsBoard(Long tacticsBoardId, Long playerId) {
        TacticsBoard tacticsBoard = tacticsBoardRepository.findById(tacticsBoardId).orElseThrow(() -> new RuntimeException("TacticsBoard not found"));
        PlayerIcon player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
        tacticsBoard.addPlayer(player);
        tacticsBoardRepository.save(tacticsBoard);
    }
//    @Override
//    public void setHomeTeam(Long tacticsBoardId, Long teamId) {
//        TacticsBoard tacticsBoard = tacticsBoardRepository.findById(tacticsBoardId).orElseThrow(() -> new RuntimeException("TacticsBoard not found"));
//        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));
//        tacticsBoard.setHomeTeam(team);
//        tacticsBoardRepository.save(tacticsBoard);
//    }
//    @Override
//    public void setAwayTeam(Long tacticsBoardId, Long teamId) {
//        TacticsBoard tacticsBoard = tacticsBoardRepository.findById(tacticsBoardId).orElseThrow(() -> new RuntimeException("TacticsBoard not found"));
//        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));
//        tacticsBoard.setAwayTeam(team);
//        tacticsBoardRepository.save(tacticsBoard);
//    }
//    @Override
//    public void updateFormation(Long tacticsBoardId, Map<String, Position> formation) {
//        TacticsBoard tacticsBoard = tacticsBoardRepository.findById(tacticsBoardId).orElseThrow(() -> new RuntimeException("TacticsBoard not found"));
//        tacticsBoard.setFormation(formation);
//        tacticsBoardRepository.save(tacticsBoard);
//    }
    @Override
    public PlayerIcon addPlayer(PlayerIcon player, Long tacticsBoardId) {
        TacticsBoard tacticsBoard = tacticsBoardRepository.findById(tacticsBoardId)
                .orElseThrow(() -> new RuntimeException("TacticsBoard not found"));
        player.setTacticsBoard(tacticsBoard);
        tacticsBoard.addPlayer(player);
        return playerRepository.save(player);
    }

    @Override
    public List<PlayerIcon> getPlayersByTacticsBoard(Long tacticsBoardId) {
        TacticsBoard tacticsBoard = tacticsBoardRepository.findById(tacticsBoardId)
                .orElseThrow(() -> new RuntimeException("TacticsBoard not found"));
        return playerRepository.findByTacticsBoard(tacticsBoard);
    }

    @Override
    public void deletePlayer(Integer jerseyNumber, Long tacticsBoardId) {
        TacticsBoard tacticsBoard = tacticsBoardRepository.findById(tacticsBoardId)
                .orElseThrow(() -> new RuntimeException("TacticsBoard not found"));
        PlayerIcon player = playerRepository.findByJerseyNumber(jerseyNumber) ;
        tacticsBoard.getPlayers().remove(player);
        playerRepository.delete(player);
        tacticsBoardRepository.save(tacticsBoard);
    }

    @Override
    public PlayerIcon updatePlayer(PlayerIcon player, Long tacticsBoardId) {
        TacticsBoard tacticsBoard = tacticsBoardRepository.findById(tacticsBoardId)
                .orElseThrow(() -> new RuntimeException("TacticsBoard not found"));
        PlayerIcon existingPlayer = playerRepository.findById(player.getIdIcon())
                .orElseThrow(() -> new RuntimeException("Player not found"));

        if (player.getJerseyNumber() != null) {
            existingPlayer.setJerseyNumber(player.getJerseyNumber());
        }
        if (player.getJerseyColor() != null) {
            existingPlayer.setJerseyColor(player.getJerseyColor());
        }
        if (player.getRole() != null) {
            existingPlayer.setRole(player.getRole());
        }
        if (player.getPosition() != null) {
            existingPlayer.setPosition(player.getPosition());
        }
        existingPlayer.setTacticsBoard(tacticsBoard);
        return playerRepository.save(existingPlayer);
    }

    @Override
    public TacticsEngagementData getEngagementData() {
        // Récupérer le nombre total de tableaux tactiques
        long totalBoards = tacticsBoardRepository.count();

        // Récupérer le nombre de tableaux modifiés (version > 0)
        long modifiedBoards = tacticsBoardRepository.countByVersionGreaterThan(0L);

        // Calculer le nombre de tableaux non modifiés
        long unchangedBoards = totalBoards - modifiedBoards;

        return new TacticsEngagementData((int) modifiedBoards, (int) unchangedBoards);
    }

    @Override
    public List<TacticsMonthlyActivity> getMonthlyActivityData() {
        // Récupérer les données d'activité mensuelle depuis le repository
        return tacticsBoardRepository.getMonthlyActivityData();
    }

    @Override
    public List<TacticsPlayerCount> getPlayerCountByBoard() {
        // Récupérer le nombre de joueurs par tableau tactique
        return tacticsBoardRepository.getPlayerCountByBoard();
    }

//    @Scheduled(cron = "0 0 0 * * *")
//    public void deleteEmptyTacticsBoards() {
//        List<TacticsBoard> emptyBoards = tacticsBoardRepository.findEmptyBoards();
//
//        if (!emptyBoards.isEmpty()) {
//            log.info("Suppression de {} Tactics Boards vides : {}",
//                    emptyBoards.size(),
//                    emptyBoards.stream().map(TacticsBoard::getIdTactic).toList());
//            tacticsBoardRepository.deleteAll(emptyBoards);
//        } else {
//            log.info("Aucun Tactics Board vide à supprimer");
//        }
//    }


}
