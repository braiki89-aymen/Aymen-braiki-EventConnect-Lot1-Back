package tn.esprit.tic.se.spr01.VideoandStrategic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.VideoandStrategic.dto.VideoAnnotationDto;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoAnalysis;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoAnnotation;
import tn.esprit.tic.se.spr01.VideoandStrategic.repositories.VideoAnalysisRepository;
import tn.esprit.tic.se.spr01.VideoandStrategic.repositories.VideoAnnotationRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoAnnotationServiceImpl {

    @Autowired
    private VideoAnnotationRepository videoAnnotationRepository;

    @Autowired
    private VideoAnalysisRepository videoAnalysisRepository;

    private VideoAnnotationDto convertToDTO(VideoAnnotation annotation) {
        return VideoAnnotationDto.builder()
                .videoAnnotationId(annotation.getVideoAnnotationId())
                .timestamp(annotation.getTimestamp())
                .comment(annotation.getComment())
                .createdAt(annotation.getCreatedAt())
                .status(annotation.getStatus())
                .annotationType(annotation.getAnnotationType())
                .build();
    }


    private VideoAnnotation convertToEntity(VideoAnnotationDto dto) {
        return VideoAnnotation.builder()
                .VideoAnnotationId(dto.getVideoAnnotationId())
                .Timestamp(dto.getTimestamp())
                .Comment(dto.getComment())
                .CreatedAt(dto.getCreatedAt())
                .status(dto.getStatus())
                .annotationType(dto.getAnnotationType())
                .build();
    }




    /*
    public VideoAnnotationDto createVideoAnnotation(VideoAnnotationDto dto) {
        VideoAnnotation annotation = convertToEntity(dto);
        VideoAnnotation savedAnnotation = videoAnnotationRepository.save(annotation);
        return convertToDTO(savedAnnotation);
    }*/

    public void AffectVideoAnalysisToVideoAnnotation(VideoAnnotation video, Long videoAnalysisId) {
        VideoAnalysis v = videoAnalysisRepository.findById(videoAnalysisId).get();
        video.setVideoAnalysisA(v);
        videoAnnotationRepository.save(video);
    }


    public List<VideoAnnotationDto> getAllAnnotations() {
        return videoAnnotationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public VideoAnnotationDto getAnnotationById(Long id) {
        return videoAnnotationRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Annotation not found"));
    }

    public VideoAnnotationDto updateAnnotation(Long id, VideoAnnotationDto dto) {
        VideoAnnotation existingAnnotation = videoAnnotationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Annotation not found"));

        existingAnnotation.setTimestamp(dto.getTimestamp());
        existingAnnotation.setComment(dto.getComment());
        existingAnnotation.setCreatedAt(dto.getCreatedAt());
        existingAnnotation.setStatus(dto.getStatus());
        existingAnnotation.setAnnotationType(dto.getAnnotationType());

        VideoAnnotation updatedAnnotation = videoAnnotationRepository.save(existingAnnotation);
        return convertToDTO(updatedAnnotation);
    }

    public void deleteAnnotation(Long id) {
        videoAnnotationRepository.deleteById(id);
    }


}
