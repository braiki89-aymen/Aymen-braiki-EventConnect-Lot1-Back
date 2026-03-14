package tn.esprit.tic.se.spr01.TacticalBoard.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsAnimationDto;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsAnimation;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsBoard;
import tn.esprit.tic.se.spr01.TacticalBoard.Repositories.TacticsAnimationRepository;
import tn.esprit.tic.se.spr01.TacticalBoard.Repositories.TacticsBoardRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TacticsAnimationService implements iTacticsAnimationService{

    TacticsAnimationRepository tacticsAnimationRepository;
    TacticsBoardRepository tacticsBoardRepository;

    public TacticsAnimation AddAnimation(TacticsAnimationDto dto) {
        TacticsBoard tacticsBoard = tacticsBoardRepository.findById(dto.tacticsBoardIdTactic()).orElseThrow(() -> new EntityNotFoundException("TacticsBoard not found with id: " + dto.tacticsBoardIdTactic()));

        TacticsAnimation animation = new TacticsAnimation();
        animation.setAnimationData(dto.animationData());
        animation.setPreviewUrl(dto.previewUrl());
        animation.setDuration(dto.duration());
        animation.setTacticsBoard(tacticsBoard);

        return tacticsAnimationRepository.save(animation);
    }

    public TacticsAnimation getAnimationById(Long id) {
        return tacticsAnimationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TacticsAnimation not found with id: " + id));
    }

    public List<TacticsAnimation> getAllAnimation() {
        return tacticsAnimationRepository.findAll();
    }

    public TacticsAnimation updateAnimation(Long id, TacticsAnimationDto dto) {
        TacticsAnimation existingAnimation = tacticsAnimationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TacticsAnimation not found with id: " + id));

        TacticsBoard tacticsBoard = tacticsBoardRepository.findById(dto.tacticsBoardIdTactic())
                .orElseThrow(() -> new EntityNotFoundException("TacticsBoard not found with id: " + dto.tacticsBoardIdTactic()));

        existingAnimation.setAnimationData(dto.animationData());
        existingAnimation.setPreviewUrl(dto.previewUrl());
        existingAnimation.setDuration(dto.duration());
        existingAnimation.setTacticsBoard(tacticsBoard);

        return tacticsAnimationRepository.save(existingAnimation);
    }

    public void deleteAnimation(Long id) {
        if (!tacticsAnimationRepository.existsById(id)) {
            throw new EntityNotFoundException("TacticsAnimation not found with id: " + id);
        }
        tacticsAnimationRepository.deleteById(id);
    }


}
