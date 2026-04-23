package tn.esprit.tic.se.spr01.UserManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tn.esprit.tic.se.spr01.Chat.messageroom.MessageRoom;
import tn.esprit.tic.se.spr01.Chat.messageroommember.MessageRoomMember;
import tn.esprit.tic.se.spr01.Communication.Entities.Chat;
import tn.esprit.tic.se.spr01.Communication.Entities.Notification;
import tn.esprit.tic.se.spr01.utils.FileUtil;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)

public class User implements Serializable , UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname", length = 255, nullable = false, unique = true)
    private String firstname;
    private String lastname;
    @Column(unique=true)
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private boolean enabled;
    private boolean accountLocked;
    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    UserRole roleUser;

    private LocalDateTime lastLogin = LocalDateTime.now();

    private String avatarUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "createdBy")
    private List<MessageRoom> messageRoom;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<MessageRoomMember> messageRoomMember;

    public String getAvatarUrl() {
        if(avatarUrl == null || avatarUrl.isEmpty()) {
            return null;
        }
        return FileUtil.getFileUrl("avatars", avatarUrl);
    }

    @JsonIgnore
    public String getAvatarShortUrl() {
        return "avatars/" + avatarUrl;
    }

    @JsonIgnore
    @ManyToMany
    private Set<Chat> chats;
    @JsonIgnore
    @ManyToMany
    private Set<Notification> notifications;



    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public String getEmail() {
        return email;
    }



    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleUser.name()));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}








