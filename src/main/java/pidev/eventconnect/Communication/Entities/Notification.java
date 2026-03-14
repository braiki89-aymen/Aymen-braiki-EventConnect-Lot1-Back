package tn.esprit.tic.se.spr01.Communication.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idNotification;
    String content;
    @Enumerated(EnumType.STRING)
    StatusNotif status;
    @ManyToMany(mappedBy = "notifications")
    Set<User> userSet;

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(StatusNotif status) {
        this.status = status;
    }


}
