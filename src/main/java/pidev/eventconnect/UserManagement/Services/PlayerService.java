package tn.esprit.tic.se.spr01.UserManagement.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.UserManagement.DTO.PlayerDto;
import tn.esprit.tic.se.spr01.UserManagement.DTO.UserDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Player;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.PlayerRepository;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.UserRepository;

import java.util.List;

@Service

@RequiredArgsConstructor
@Slf4j
public class PlayerService implements iPlayerService {

    @Autowired
    PlayerRepository playerRepository;


    // Create
    @Override
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    // Read
    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found"));
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    // Update
    @Override
    public Player updatePlayer(Long id, Player player) {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found"));
        existingPlayer.setFirstname(player.getFirstname());
        existingPlayer.setLastname(player.getLastname());
        existingPlayer.setEmail(player.getEmail());
        existingPlayer.setPassword(player.getPassword());
        existingPlayer.setDateOfBirth(player.getDateOfBirth());
        existingPlayer.setEnabled(player.isEnabled());
        existingPlayer.setAccountLocked(player.isAccountLocked());
        existingPlayer.setRole(player.getRole());
        existingPlayer.setJerseyNumber(player.getJerseyNumber());
        existingPlayer.setHealthStatus(player.getHealthStatus());
        return playerRepository.save(existingPlayer);
    }

    // Delete
    @Override
    public void deletePlayer(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new EntityNotFoundException("Player not found");
        }
        playerRepository.deleteById(id);
    }

}
