package tn.esprit.tic.se.spr01.VideoandStrategic.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long idPost;
    private String clubName;
    private String postedBy;
    @Column(length = 5000)
    private String content;
    @Column(length = 5000)
    private String img;
    private Date date;
    private int likeCount;
    private int viewCount;
    //private List<String> tags;


    public Date getDate() {
        return (date == null) ? new Date() : date;
    }
}
