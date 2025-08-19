package pidev.eventconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.eventconnect.entities.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    @Query("SELECT e FROM Event e WHERE " +
            "(e.startDate <= :endDate AND e.endDate >= :startDate) " +
            "AND e.place = :place")
    List<Event> findConflictingEvents(@Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate,
                                      @Param("place") String place);
}
