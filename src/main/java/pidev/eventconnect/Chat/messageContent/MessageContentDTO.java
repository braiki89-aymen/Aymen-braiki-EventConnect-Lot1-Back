package tn.esprit.tic.se.spr01.Chat.messageContent;

import lombok.Data;
import tn.esprit.tic.se.spr01.UserManagement.DTO.UserWithAvatarDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MessageContentDTO {
    private UUID id;
    private String content;
    private LocalDateTime dateSent;
    private MessageType messageType;
    private UUID messageRoomId;
    private UserWithAvatarDTO sender;
}
