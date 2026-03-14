package tn.esprit.tic.se.spr01.TacticalBoard.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsStatsDto;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsStats;
import tn.esprit.tic.se.spr01.TacticalBoard.Services.iTacticsStatsService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tacticsStats")
public class TacticsStatsController {

    iTacticsStatsService tacticsStatsService;

    @PostMapping("/addStats")
    @ResponseBody
    public TacticsStats addStats(@Valid @RequestBody TacticsStatsDto dto) {
        return tacticsStatsService.addTacticsStats(dto);
    }

    @GetMapping("/getStats/{id}")
    @ResponseBody
    public TacticsStats getStatsById(@PathVariable Long id) {
        return tacticsStatsService.getTacticsStatsById(id);
    }

    @GetMapping("/getAllStats")
    @ResponseBody
    public List<TacticsStats> getAllStats() {
        return tacticsStatsService.getAllTacticsStats();
    }

    @PutMapping("/updateStats/{id}")
    @ResponseBody
    public TacticsStats updateStats(@PathVariable Long id, @Valid @RequestBody TacticsStatsDto dto) {
        return tacticsStatsService.updateTacticsStats(id, dto);
    }

    @DeleteMapping("/deleteStats/{id}")
    @ResponseBody
    public void deleteStats(@PathVariable Long id) {
        tacticsStatsService.deleteTacticsStats(id);
    }
}