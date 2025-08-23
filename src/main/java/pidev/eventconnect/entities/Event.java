package pidev.eventconnect.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    String title;
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    String description;
    @NotNull(message = "Start date is required")

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;
    @NotNull(message = "End date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    @NotBlank(message = "Place is required")
    @Size(min = 2, max = 100, message = "Place must be between 2 and 100 characters")
    String place;
    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 10000, message = "Capacity must not exceed 10000")
    Long capacityMax;
    Double price;
    Long nbParticipantsActuels = 0L;
    @OneToMany(mappedBy = "event" ,cascade = CascadeType.ALL)

    List<Reservation> reservations = new ArrayList<>();
    @OneToMany (mappedBy = "event", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<FeedBack> feedBacks;

}
