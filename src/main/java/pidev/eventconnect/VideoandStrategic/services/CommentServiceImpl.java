package tn.esprit.tic.se.spr01.VideoandStrategic.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.VideoandStrategic.dto.CommentDto;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.Comment;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.Post;
import tn.esprit.tic.se.spr01.VideoandStrategic.repositories.CommentRepository;
import tn.esprit.tic.se.spr01.VideoandStrategic.repositories.PostRepository;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    private static final List<String> BAD_WORDS = Arrays.asList("fuck","bitch","son of bitch","mother fucker", "puta", "nigga", "qahba", "miboun");
    private boolean containsBadWords(String content) {
        for (String badWord : BAD_WORDS) {
            if (content.toLowerCase().contains(badWord.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public Comment createComment(Long postId, String postedBy, String content){
        if (containsBadWords(content) || containsBadWords(postedBy)) {
            throw new IllegalArgumentException("Comment contains inappropriate language.");
        }

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Comment comment = new Comment();
            comment.setPost(optionalPost.get());
            comment.setContent(content);
            comment.setPostedBy(postedBy);
            comment.setCreatedAt(new Date());
            return commentRepository.save(comment);
        }
        throw new EntityNotFoundException("Post not found");
    }

    public List<Comment>getCommentsByPostId(Long postId){
        return commentRepository.findByPostId(postId);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public String simulatePullRequestReview(String title, String body) {
    if (title == null || title.isBlank()) {
        return "PR invalide : titre manquant";
    }

    if (body == null || body.isBlank()) {
        return "PR invalide : description manquante";
    }

    if (containsBadWords(title) || containsBadWords(body)) {
        return "PR rejetée : contenu inapproprié";
    }

    return "PR valide : prête pour review";
}
}

