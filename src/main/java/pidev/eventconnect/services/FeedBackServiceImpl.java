package pidev.eventconnect.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.eventconnect.entities.Event;
import pidev.eventconnect.entities.FeedBack;
import pidev.eventconnect.repository.EventRepository;
import pidev.eventconnect.repository.FeedBackRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedBackServiceImpl implements IFeedBackService{
    @Autowired
    FeedBackRepository feedBackRepository;
    @Autowired
    EventRepository eventRepository;
    @Override
    public FeedBack addFeedback(FeedBack feedback,Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + id));

        feedback.setEvent(event);

        return feedBackRepository.save(feedback);
    }
    @Override
    public List<FeedBack> getFeedbacksByEvent(Long id) {
        return feedBackRepository.findByEventId(id);
    }
    @Override
    public double getAverageRating(Long id) {
        List<FeedBack> feedbacks = feedBackRepository.findByEventId(id);
        return feedbacks.stream()
                .mapToInt(FeedBack::getRating)
                .average()
                .orElse(0.0);
    }
}
