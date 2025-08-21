package pidev.eventconnect.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.eventconnect.entities.FeedBack;
import pidev.eventconnect.services.FeedBackServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/feedback")
@CrossOrigin(origins = "*")
public class FeedBackRestController {

    @Autowired
    FeedBackServiceImpl feedBackService;

    @PostMapping("/add/{id}")
    public FeedBack addFeedback(@RequestBody FeedBack feedback, @PathVariable Long id) {
        return feedBackService.addFeedback(feedback,id);
    }

    @GetMapping("/event/{id}")
    public List<FeedBack> getFeedbacks(@PathVariable Long id) {
        return feedBackService.getFeedbacksByEvent(id);
    }

    @GetMapping("/event/{id}/average")
    public double getAverageRating(@PathVariable Long id) {
        return feedBackService.getAverageRating(id);
    }
}
