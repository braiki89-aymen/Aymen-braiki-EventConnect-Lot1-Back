package tn.esprit.tic.se.spr01.VideoandStrategic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.VideoandStrategic.dto.VideoStatisticsDto;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoAnalysis;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoStatistics;
import tn.esprit.tic.se.spr01.VideoandStrategic.repositories.VideoAnalysisRepository;
import tn.esprit.tic.se.spr01.VideoandStrategic.repositories.VideoStatisticsRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoStatisticsServiceImpl implements VideoStatisticsService {

    @Autowired
    private VideoStatisticsRepository videoStatisticsRepository;

    @Autowired
    private VideoAnalysisRepository videoAnalysisRepository;


    @Override
    public VideoStatistics create(VideoStatistics stats) {
        return videoStatisticsRepository.save(stats);
    }

    @Override
    public VideoStatistics update(Long id, VideoStatistics stats) {
        VideoStatistics existing = videoStatisticsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Statistics not found"));
        stats.setVideoStatisticsId(id);
        return videoStatisticsRepository.save(stats);
    }

    @Override
    public void delete(Long id) {
        videoStatisticsRepository.deleteById(id);
    }

    @Override
    public VideoStatistics getById(Long id) {
        return videoStatisticsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Statistics not found"));
    }

    @Override
    public List<VideoStatistics> getAll() {
        return videoStatisticsRepository.findAll();
    }

}
