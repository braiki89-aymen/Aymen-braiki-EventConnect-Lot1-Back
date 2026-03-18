package tn.esprit.tic.se.spr01.Chat.messageroommember;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MessageRoomMemberDTO {
    private UUID messageRoomId;
    private String firstname;
    private String avatarUrl;
    private Boolean isAdmin;
    private LocalDateTime lastSeen;
    private LocalDateTime lastLogin;
}
