package tn.esprit.tic.se.spr01.VideoandStrategic.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tic.se.spr01.VideoandStrategic.dto.VideoAnalysisDto;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoAnalysis;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoAnalysisType;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoStatus;
import tn.esprit.tic.se.spr01.VideoandStrategic.services.VideoAnalysisService;
import tn.esprit.tic.se.spr01.VideoandStrategic.services.VideoAnalysisServiceImpl;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VideoAnalysisController {

    @Autowired
    private final VideoAnalysisService videoAnalysisService;

    @PostMapping("/createVideo")
    public VideoAnalysis create(@RequestParam("title")String title,
                                @RequestParam("description") String description,
                                @RequestParam("videoUrl") MultipartFile videoUrl,
                                @RequestParam("analysisType") VideoAnalysisType analysisType,
                                @RequestParam("status") VideoStatus status) {
        return videoAnalysisService.create(title, description, videoUrl, analysisType, status);
    }

    @PutMapping("/updateVideo/{id}")
    public ResponseEntity<VideoAnalysis> update(@PathVariable("id") Long id,
                                                @RequestParam("title") String title,
                                                @RequestParam("description") String description,
                                                @RequestParam(value = "videoUrl", required = false) MultipartFile videoUrl,
                                                @RequestParam("analysisType") VideoAnalysisType analysisType,
                                                @RequestParam("status") VideoStatus status) {
        try {
            VideoAnalysis updated = videoAnalysisService.update(id, title, description, videoUrl, analysisType, status);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteVideo/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        videoAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAllVideos")
    public ResponseEntity<List<VideoAnalysis>> getAll() {
        return ResponseEntity.ok(videoAnalysisService.getAll());
    }

    @GetMapping("/getVideoById/{id}")
    public ResponseEntity<VideoAnalysis> getById(@PathVariable Long id) {
        return ResponseEntity.ok(videoAnalysisService.getById(id));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchByName(@PathVariable String title) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(videoAnalysisService.searchByName(title));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}





