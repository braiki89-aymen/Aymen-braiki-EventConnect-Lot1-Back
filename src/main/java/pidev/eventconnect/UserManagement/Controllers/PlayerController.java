package tn.esprit.tic.se.spr01.UserManagement.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.UserManagement.DTO.PlayerDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Player;
import tn.esprit.tic.se.spr01.UserManagement.Services.iPlayerService;

import java.util.List;

@RestController

@RequestMapping("/players")
public class PlayerController {

    final
    iPlayerService playerService;

    public PlayerController(iPlayerService playerService) {
        this.playerService = playerService;
    }


//    @PostMapping("/addPlayer")
//    @ResponseBody
//    public Player addPlayer(@Valid @RequestBody PlayerDto dto) {
//        return playerService.addPlayer(dto);
//    }


    @PostMapping("/addPlayer")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        return ResponseEntity.ok(playerService.addPlayer(player));
    }

    @GetMapping("/getPlayer/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @PutMapping("/updatePlayer/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return ResponseEntity.ok(playerService.updatePlayer(id, player));
    }

    @DeleteMapping("/deletePlayer/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }


}