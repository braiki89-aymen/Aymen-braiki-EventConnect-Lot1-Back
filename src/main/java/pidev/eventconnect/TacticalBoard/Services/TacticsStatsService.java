package tn.esprit.tic.se.spr01.TacticalBoard.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsStatsDto;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsStats;
import tn.esprit.tic.se.spr01.TacticalBoard.Repositories.TacticsStatsRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TacticsStatsService implements iTacticsStatsService{

    TacticsStatsRepository tacticsStatsRepository;

    public TacticsStats addTacticsStats(TacticsStatsDto dto) {
        TacticsStats stats = new TacticsStats();
        stats.setUsageCount(dto.usageCount());
        stats.setAverageSuccessRating(dto.averageSuccessRating());

        return tacticsStatsRepository.save(stats);
    }

    public TacticsStats getTacticsStatsById(Long id) {
        return tacticsStatsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TacticsStats not found with id: " + id));
    }

    public List<TacticsStats> getAllTacticsStats() {
        return tacticsStatsRepository.findAll();
    }

    public TacticsStats updateTacticsStats(Long id, TacticsStatsDto dto) {
        TacticsStats existingStats = tacticsStatsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TacticsStats not found with id: " + id));

        existingStats.setUsageCount(dto.usageCount());
        existingStats.setAverageSuccessRating(dto.averageSuccessRating());

        return tacticsStatsRepository.save(existingStats);
    }
    public void deleteTacticsStats(Long id) {
        if (!tacticsStatsRepository.existsById(id)) {
            throw new EntityNotFoundException("TacticsStats not found with id: " + id);
        }
        tacticsStatsRepository.deleteById(id);
    }
}
