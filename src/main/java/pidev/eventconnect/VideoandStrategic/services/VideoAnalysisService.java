package tn.esprit.tic.se.spr01.VideoandStrategic.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.*;

import java.util.List;

public interface VideoAnalysisService {
    VideoAnalysis create(String title, String description, MultipartFile videoUrl, VideoAnalysisType analysisType, VideoStatus status);

    VideoAnalysis update(Long id, String title, String description, MultipartFile videoUrl,
                         VideoAnalysisType analysisType, VideoStatus status);
    void delete(Long id);
    VideoAnalysis getById(Long id);
    List<VideoAnalysis> getAll();
    List<VideoAnalysis> searchByName(String clubName);
}
