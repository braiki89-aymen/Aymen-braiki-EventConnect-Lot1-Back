package tn.esprit.tic.se.spr01.Communication.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idChat;
    Long idSender;
    Long idReceiver;
    String message;
    LocalDate date;
    @ManyToMany(mappedBy = "chats")
    Set<User> userSet;

    public void setIdSender(Long idSender) {
        this.idSender = idSender;
    }

    public void setIdReceiver(Long idReceiver) {
        this.idReceiver = idReceiver;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
