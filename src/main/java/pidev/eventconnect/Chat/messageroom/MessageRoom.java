package tn.esprit.tic.se.spr01.Chat.messageroom;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tn.esprit.tic.se.spr01.Chat.messageContent.MessageContent;
import tn.esprit.tic.se.spr01.Chat.messageroommember.MessageRoomMember;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "message_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class MessageRoom {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private Boolean isGroup;

    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "createdBy",             // colonne de message_content
            referencedColumnName = "firstname",
            columnDefinition = "VARCHAR(255)"  )
    @com.fasterxml.jackson.annotation.JsonIgnore
    private User createdBy;

    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<MessageRoomMember> members;

    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<MessageContent> messageContents;
}
