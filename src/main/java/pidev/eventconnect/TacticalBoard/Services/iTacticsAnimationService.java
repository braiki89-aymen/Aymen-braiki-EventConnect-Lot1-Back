package tn.esprit.tic.se.spr01.TacticalBoard.Services;

import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsAnimationDto;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsAnimation;

import java.util.List;

public interface iTacticsAnimationService {

    TacticsAnimation AddAnimation(TacticsAnimationDto dto);

    TacticsAnimation getAnimationById(Long id);
    List<TacticsAnimation> getAllAnimation();
    TacticsAnimation updateAnimation(Long id, TacticsAnimationDto dto);
    void deleteAnimation(Long id);
}
