package tn.esprit.tic.se.spr01.VideoandStrategic.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.Post;

import java.util.Date;
import java.util.List;

public interface PostService {
    Post addPost(String clubName, String postedBy, String content, MultipartFile img, Date date );

    Post updatePost(Long idPost, String clubName, String postedBy, String content, MultipartFile img, Date date);

    void deletePost(Long id);
    Post getPostById(Long id);
    List<Post> getAllPosts();
    List<Post> searchByName(String clubName);

    Page<Post> getAllPostsPaginated(Pageable pageable);

}
