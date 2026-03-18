package tn.esprit.tic.se.spr01.VideoandStrategic.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.VideoandStrategic.dto.VideoAnnotationDto;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoAnnotation;
import tn.esprit.tic.se.spr01.VideoandStrategic.services.VideoAnnotationServiceImpl;


import java.util.List;

@RestController
@RequestMapping("/api/annotations")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VideoAnnotationController {

    private final VideoAnnotationServiceImpl videoAnnotationService;

    /*
    @PostMapping("/createAnnotation")
    public ResponseEntity<VideoAnnotationDto> createAnnotation(@RequestBody VideoAnnotationDto dto) {
        return ResponseEntity.ok(videoAnnotationService.createVideoAnnotation(dto));
    }*/


    @PostMapping("/AffectVideoAnalysisToVideoAnnotation/{videoAnalysisId}")
    public ResponseEntity<Void> AffectVideoAnalysisToVideoAnnotation(@RequestBody VideoAnnotation video, @PathVariable Long videoAnalysisId){
        videoAnnotationService.AffectVideoAnalysisToVideoAnnotation(video,videoAnalysisId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/getAllAnnotations")
    public ResponseEntity<List<VideoAnnotationDto>> getAllAnnotations() {
        return ResponseEntity.ok(videoAnnotationService.getAllAnnotations());
    }

    @GetMapping("/getAnnotationById/{id}")
    public ResponseEntity<VideoAnnotationDto> getAnnotationById(@PathVariable Long id) {
        return ResponseEntity.ok(videoAnnotationService.getAnnotationById(id));
    }

    @PutMapping("/updateAnnotation/{id}")
    public ResponseEntity<VideoAnnotationDto> updateAnnotation(@PathVariable Long id, @RequestBody VideoAnnotationDto dto) {
        return ResponseEntity.ok(videoAnnotationService.updateAnnotation(id, dto));
    }

    @DeleteMapping("/deleteAnnotation/{id}")
    public ResponseEntity<Void> deleteAnnotation(@PathVariable Long id) {
        videoAnnotationService.deleteAnnotation(id);
        return ResponseEntity.noContent().build();
    }




}