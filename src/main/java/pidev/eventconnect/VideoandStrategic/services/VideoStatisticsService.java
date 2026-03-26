package tn.esprit.tic.se.spr01.VideoandStrategic.services;

import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoStatistics;

import java.util.List;

public interface VideoStatisticsService {
    VideoStatistics create(VideoStatistics stats);
    VideoStatistics update(Long id, VideoStatistics stats);
    void delete(Long id);
    VideoStatistics getById(Long id);
    List<VideoStatistics> getAll();
}
