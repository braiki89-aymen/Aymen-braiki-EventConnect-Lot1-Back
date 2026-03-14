package tn.esprit.tic.se.spr01.TacticalBoard.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsVideo;
import tn.esprit.tic.se.spr01.TacticalBoard.Services.TacticsVideoService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "http://localhost:4200")
public class TacticsVideoController {

    @Autowired
    private TacticsVideoService tacticsVideoService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadVideo(
            @RequestParam("video") MultipartFile video,
            @RequestParam("tacticsBoardId") Long tacticsBoardId,
            @RequestParam("players") String players) {

        Map<String, Object> response = new HashMap<>();
        try {
            TacticsVideo tacticsVideo = tacticsVideoService.saveVideo(video, tacticsBoardId, players);
            response.put("success", true);
            response.put("message", "Video uploaded successfully");
            response.put("filename", tacticsVideo.getFilename());
            response.put("id", tacticsVideo.getId());
            response.put("tacticsBoardId", tacticsBoardId);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "Failed to upload video: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

