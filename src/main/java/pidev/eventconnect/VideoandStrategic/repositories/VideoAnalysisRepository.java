package tn.esprit.tic.se.spr01.VideoandStrategic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.Post;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.VideoAnalysis;

import java.util.List;

@Repository
public interface VideoAnalysisRepository extends JpaRepository<VideoAnalysis, Long> {

    List<VideoAnalysis> findAllByTitleContaining(String title);

}
