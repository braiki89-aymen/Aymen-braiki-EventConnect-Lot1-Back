package pidev.eventconnect.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

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
    Integer nbPlace;
    @Enumerated(EnumType.STRING)
    Status status;
    String cancelCode;
    @Lob
    String qrCodeBase64;
    double amount;
    @ManyToOne
    @JsonIgnore
    Event event;

}
