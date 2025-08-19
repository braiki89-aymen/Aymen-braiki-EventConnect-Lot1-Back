package pidev.eventconnect.services;

import pidev.eventconnect.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IEventService {

    public Event addEvent (Event event);
    public Page<Event> getAllEvent (Pageable pageable);
    public void removeEvent (Long  id);
    public  Event updateEvent (Long id , Event event);
    public Event retrieveEvent (Long id);
}
