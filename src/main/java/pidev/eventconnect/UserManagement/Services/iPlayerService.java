package tn.esprit.tic.se.spr01.UserManagement.Services;

import tn.esprit.tic.se.spr01.UserManagement.DTO.PlayerDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Player;

import java.util.List;

public interface iPlayerService {

    Player addPlayer(Player player);
    Player getPlayerById(Long id);
    List<Player> getAllPlayers();
    Player updatePlayer(Long id, Player player);
    void deletePlayer(Long id);
}
