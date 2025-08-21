package pidev.eventconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.eventconnect.entities.FeedBack;

import java.util.List;

public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {

    List<FeedBack> findByEventId(Long eventId);
}
