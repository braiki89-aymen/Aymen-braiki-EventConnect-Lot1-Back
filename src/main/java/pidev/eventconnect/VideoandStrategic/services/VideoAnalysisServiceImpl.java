package tn.esprit.tic.se.spr01.VideoandStrategic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoAnalysis;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoAnalysisType;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoStatus;
import tn.esprit.tic.se.spr01.VideoandStrategic.repositories.VideoAnalysisRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VideoAnalysisServiceImpl implements VideoAnalysisService {

    private final VideoAnalysisRepository videoAnalysisRepository;

    private final FileNamingUtil fileNamingUtil;
    @Value("${Upload_video}")
    private  String Upload_video;

    @Override
    public VideoAnalysis create(String title, String description, MultipartFile videoUrl, VideoAnalysisType analysisType,
                                VideoStatus status) {
        try {
            // Vérifier l'existence du répertoire de téléchargement, le créer si nécessaire
            Path uploadPath = Paths.get(Upload_video);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = fileNamingUtil.nameFile(videoUrl);

            Path destinationPath = uploadPath.resolve(fileName);
            Files.copy(videoUrl.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            VideoAnalysis newVideoAnalysis = new VideoAnalysis();
            newVideoAnalysis.setTitle(title);
            newVideoAnalysis.setDescription(description);
            newVideoAnalysis.setVideoUrl(fileName.toString());
            newVideoAnalysis.setAnalysisType(analysisType);
            newVideoAnalysis.setStatus(status);

            return videoAnalysisRepository.save(newVideoAnalysis);
        } catch (IOException e) {

            throw new RuntimeException("Failed to upload video", e);
        }
    }

    @Override
    public VideoAnalysis update(Long id, String title, String description, MultipartFile videoUrl,
                                VideoAnalysisType analysisType, VideoStatus status) {
        VideoAnalysis existingVideoAnalysis = videoAnalysisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VideoAnalysis not found"));

        try {
            if (videoUrl != null && !videoUrl.isEmpty()) {
                Path uploadPath = Paths.get(Upload_video);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String fileName = fileNamingUtil.nameFile(videoUrl);
                Path destinationPath = uploadPath.resolve(fileName);
                Files.copy(videoUrl.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                existingVideoAnalysis.setVideoUrl(fileName);
            }

            existingVideoAnalysis.setTitle(title);
            existingVideoAnalysis.setDescription(description);
            existingVideoAnalysis.setAnalysisType(analysisType);
            existingVideoAnalysis.setStatus(status);

            return videoAnalysisRepository.save(existingVideoAnalysis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to update video", e);
        }
    }

    @Override
    public void delete(Long id) {
        videoAnalysisRepository.deleteById(id);
    }

    @Override
    public VideoAnalysis getById(Long id) {
        return videoAnalysisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VideoAnalysis not found"));
    }

    @Override
    public List<VideoAnalysis> getAll() {
        return videoAnalysisRepository.findAll();
    }

    public List<VideoAnalysis> searchByName(String title) {
        return videoAnalysisRepository.findAllByTitleContaining(title);
    }
}
