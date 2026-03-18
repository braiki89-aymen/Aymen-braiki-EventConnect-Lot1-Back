package tn.esprit.tic.se.spr01.VideoandStrategic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.VideoandStrategic.dto.VideoStatisticsDto;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoStatistics;
import tn.esprit.tic.se.spr01.VideoandStrategic.services.VideoStatisticsServiceImpl;


import java.util.List;

@RestController
@RequestMapping("/api/video-statistics")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VideoStatisticsController {

    @Autowired
    private VideoStatisticsServiceImpl videoStatisticsService;


    @PostMapping("/createVideoStatistic")
    public ResponseEntity<VideoStatistics> create(@RequestBody VideoStatistics stats) {
        return ResponseEntity.ok(videoStatisticsService.create(stats));
    }

    @PutMapping("/updateVideoStatistics/{id}")
    public ResponseEntity<VideoStatistics> update(@PathVariable Long id, @RequestBody VideoStatistics stats) {
        return ResponseEntity.ok(videoStatisticsService.update(id, stats));
    }

    @DeleteMapping("/deleteVideoStatistics/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        videoStatisticsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getVideoStatisticsById/{id}")
    public ResponseEntity<VideoStatistics> getById(@PathVariable Long id) {
        return ResponseEntity.ok(videoStatisticsService.getById(id));
    }

    @GetMapping("/getAllVideoStatistics")
    public ResponseEntity<List<VideoStatistics>> getAll() {
        return ResponseEntity.ok(videoStatisticsService.getAll());
    }

}
