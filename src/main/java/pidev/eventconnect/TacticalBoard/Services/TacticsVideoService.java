package tn.esprit.tic.se.spr01.TacticalBoard.Services;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsBoard;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsVideo;
import tn.esprit.tic.se.spr01.TacticalBoard.Repositories.TacticsBoardRepository;
import tn.esprit.tic.se.spr01.TacticalBoard.Repositories.TacticsVideoRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class TacticsVideoService  {

    @Autowired
    private TacticsVideoRepository tacticsVideoRepository;

    @Autowired
    private TacticsBoardRepository tacticsBoardRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Value("${video.upload.path}")
    private String uploadPathStr;

    private Path uploadPath;

    @PostConstruct
    public void init() {
        uploadPath = Paths.get(uploadPathStr);
        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    /**
     * Save a video file and its metadata
     *
     * @param video The video file to save
     * @param tacticsBoardId The ID of the associated tactics board
     * @param playerData JSON string containing player information
     * @return The saved TacticsVideo entity
     * @throws IOException If there's an error saving the file
     */
    public TacticsVideo saveVideo(MultipartFile video, Long tacticsBoardId, String playerData) throws IOException {
        TacticsBoard tacticsBoard = tacticsBoardRepository.findById(tacticsBoardId)
                .orElseThrow(() -> new RuntimeException("TacticsBoard not found"));

        // Upload video to Cloudinary
        String videoUrl = cloudinaryService.uploadVideo(video);

        // Create and save TacticsVideo entity
        TacticsVideo tacticsVideo = new TacticsVideo(
                video.getOriginalFilename(),
                videoUrl,
                tacticsBoard,
                playerData
        );
        return tacticsVideoRepository.save(tacticsVideo);
    }
}
