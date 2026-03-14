package tn.esprit.tic.se.spr01.TacticalBoard.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.TacticalBoard.DTO.TacticsAnimationDto;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsAnimation;
import tn.esprit.tic.se.spr01.TacticalBoard.Services.iTacticsAnimationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tacticsAnimations")
public class TacticsAnimationController {

    iTacticsAnimationService tacticsAnimationService;

    @PostMapping("/addAnimation")
    @ResponseBody
    public TacticsAnimation addAnimation(@Valid @RequestBody TacticsAnimationDto dto) {
        return tacticsAnimationService.AddAnimation(dto);
    }

    @GetMapping("/getAnimation/{id}")
    @ResponseBody
    public TacticsAnimation getAnimationById(@PathVariable Long id) {
        return tacticsAnimationService.getAnimationById(id);
    }

    @GetMapping("/getAllAnimations")
    @ResponseBody
    public List<TacticsAnimation> getAllAnimations() {
        return tacticsAnimationService.getAllAnimation();
    }

    @PutMapping("/updateAnimation/{id}")
    @ResponseBody
    public TacticsAnimation updateAnimation(@PathVariable Long id, @Valid @RequestBody TacticsAnimationDto dto) {
        return tacticsAnimationService.updateAnimation(id, dto);
    }

    @DeleteMapping("/deleteAnimation/{id}")
    @ResponseBody
    public void deleteAnimation(@PathVariable Long id) {
        tacticsAnimationService.deleteAnimation(id);
    }
}
