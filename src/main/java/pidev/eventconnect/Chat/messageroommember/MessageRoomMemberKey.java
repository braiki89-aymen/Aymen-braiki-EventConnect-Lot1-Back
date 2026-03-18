package tn.esprit.tic.se.spr01.Chat.messageroommember;

import lombok.Data;
import tn.esprit.tic.se.spr01.Chat.messageroom.MessageRoom;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;

import java.io.Serializable;
import java.util.UUID;

@Data
public class MessageRoomMemberKey implements Serializable {
    private User user;
    private MessageRoom messageRoom;
}