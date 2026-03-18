// Comment DTO
package tn.esprit.tic.se.spr01.VideoandStrategic.dto;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long idComment;
    private String content;
    private Date createdAt;
}
