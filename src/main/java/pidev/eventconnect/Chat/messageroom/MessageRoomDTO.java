package tn.esprit.tic.se.spr01.Chat.messageroom;

import lombok.Data;
import tn.esprit.tic.se.spr01.Chat.messageContent.MessageContentDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MessageRoomDTO {
    private UUID id;
    private String name;
    private Boolean isGroup;
    private LocalDateTime createdAt;
    private String createdById;
    private MessageContentDTO lastMessage;
    private Long unSeenCount;
    private String avatarUrl;
}
