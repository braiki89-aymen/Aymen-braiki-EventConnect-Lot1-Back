package tn.esprit.tic.se.spr01.TacticalBoard.Services;

import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsStatsDto;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsStats;

import java.util.List;

public interface iTacticsStatsService {

    TacticsStats addTacticsStats(TacticsStatsDto dto);
    TacticsStats getTacticsStatsById(Long id);
    List<TacticsStats> getAllTacticsStats();
    TacticsStats updateTacticsStats(Long id, TacticsStatsDto dto);
    void deleteTacticsStats(Long id);
}
