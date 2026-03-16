package tn.esprit.tic.se.spr01.Chat.messageroommember;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.tic.se.spr01.Chat.messageroom.MessageRoom;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "message_room_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(MessageRoomMemberKey.class)
@Builder
public class MessageRoomMember {
    @Id
    @ManyToOne
    @JoinColumn(name = "message_room_id")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private MessageRoom messageRoom;

    @Id
    @ManyToOne
    @JoinColumn(name = "firstname",             // colonne de message_content
            referencedColumnName = "firstname",
            columnDefinition = "VARCHAR(255)"  )
    @com.fasterxml.jackson.annotation.JsonIgnore
    private User user;

    private Boolean isAdmin;

    private LocalDateTime lastSeen;
}