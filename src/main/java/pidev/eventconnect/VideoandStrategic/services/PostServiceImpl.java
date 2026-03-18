package tn.esprit.tic.se.spr01.VideoandStrategic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tic.se.spr01.VideoandStrategic.entities.Post;

import tn.esprit.tic.se.spr01.VideoandStrategic.repositories.PostRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {


    private final  PostRepository postRepository;

    private final FileNamingUtil fileNamingUtil;
    @Value("${Upload_image}")
    private  String Upload_image;


    @Override
    public Post addPost(String clubName, String postedBy, String content, MultipartFile img,Date date) {
        try {
            Path uploadPath = Paths.get(Upload_image);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = fileNamingUtil.nameFile(img);
            Path destinationPath = uploadPath.resolve(fileName);
            Files.copy(img.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            Post newPost = new Post();
            newPost.setClubName(clubName);
            newPost.setPostedBy(postedBy);
            newPost.setContent(content);
            newPost.setImg(fileName.toString());


            newPost.setDate(date != null ? date : new Date());

            return postRepository.save(newPost);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload video", e);
        }
    }




    @Override
    public Post updatePost(Long idPost, String clubName, String postedBy, String content, MultipartFile img, Date date) {
        try {
            Post existingPost = postRepository.findById(idPost).orElseThrow(() -> new RuntimeException("Post not found"));

            existingPost.setClubName(clubName);
            existingPost.setPostedBy(postedBy);
            existingPost.setContent(content);
            existingPost.setDate(date != null ? date : existingPost.getDate());

            if (img != null && !img.isEmpty()) {
                // Save the new image
                Path uploadPath = Paths.get(Upload_image);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String fileName = fileNamingUtil.nameFile(img);
                Path destinationPath = uploadPath.resolve(fileName);
                Files.copy(img.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                existingPost.setImg(fileName.toString());
            }

            return postRepository.save(existingPost);
        } catch (IOException e) {
            throw new RuntimeException("Failed to update post", e);
        }
    }


    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.getComments().clear(); // optional if using cascade+orphanRemoval
            postRepository.delete(post); // this will cascade if set correctly
        }
    }


    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> searchByName(String clubName) {
        return postRepository.findAllByClubNameContaining(clubName);
    }


    @Override
    public Page<Post> getAllPostsPaginated(Pageable pageable) {
        return postRepository.findAll(pageable);
    }


}
