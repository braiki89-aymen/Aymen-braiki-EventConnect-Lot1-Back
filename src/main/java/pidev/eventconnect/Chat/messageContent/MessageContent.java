package tn.esprit.tic.se.spr01.Chat.messageContent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tn.esprit.tic.se.spr01.Chat.messageroom.MessageRoom;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "message_content")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class MessageContent {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;

    private String content;

    @CreatedDate
    private LocalDateTime dateSent;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @ManyToOne
    @JoinColumn(name = "message_room_id")
    private MessageRoom messageRoom;

    @ManyToOne
    @JoinColumn(
            name = "firstname",             // colonne de message_content
            referencedColumnName = "firstname",
            columnDefinition = "VARCHAR(255)"   // colonne unique de user
    )
    private User user;
}

