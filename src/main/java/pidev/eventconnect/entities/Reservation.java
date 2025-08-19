package pidev.eventconnect.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstNameParticipant;
    String lastNameParticipant;
    String emailParticipant;
    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne
    Event event;

}
