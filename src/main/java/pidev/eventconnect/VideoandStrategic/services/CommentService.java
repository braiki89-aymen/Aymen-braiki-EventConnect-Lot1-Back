package tn.esprit.tic.se.spr01.VideoandStrategic.services;

import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.Comment;

import java.util.List;


public interface CommentService {
    Comment createComment(Long postId, String postedBy, String content);
    List<Comment>getCommentsByPostId(Long postId);
    void deleteComment(Long id);
    String simulatePullRequestReview(String title, String body);
}
