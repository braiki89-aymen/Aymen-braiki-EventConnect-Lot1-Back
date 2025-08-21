package pidev.eventconnect.services;

import pidev.eventconnect.entities.FeedBack;

import java.util.List;

public interface IFeedBackService {

    public FeedBack addFeedback(FeedBack feedback,Long id);
    public List<FeedBack> getFeedbacksByEvent(Long id);
    public double getAverageRating(Long id);
}
