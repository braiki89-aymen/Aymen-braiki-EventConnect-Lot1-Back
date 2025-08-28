package pidev.eventconnect.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pidev.eventconnect.entities.Event;
import pidev.eventconnect.entities.Status;
import pidev.eventconnect.repository.EventRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.io.FileWriter;
import java.io.PrintWriter;
import pidev.eventconnect.entities.Reservation;
import pidev.eventconnect.entities.FeedBack;
@Service
@AllArgsConstructor
public class EventServiceImpl implements IEventService{
   @Autowired
    EventRepository eventRepository;


    @Override
    public Event addEvent(Event event) {

        List<Event> conflicts = eventRepository.findConflictingEvents(
                event.getStartDate(),
                event.getEndDate(),
                event.getPlace()
        );

        if (!conflicts.isEmpty()) {
            LocalDate newStart = event.getStartDate();
            LocalDate newEnd = event.getEndDate();

            while (!eventRepository.findConflictingEvents(newStart, newEnd, event.getPlace()).isEmpty()) {
                newStart = newStart.plusDays(1);
                newEnd = newEnd.plusDays(1);
            }

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,  // 409 au lieu de 500
                    "Conflict detected at " + event.getPlace() +
                            ". Suggested new date: " + newStart + " - " + newEnd
            );
        }

        return eventRepository.save(event);
    }


    @Override
    public Page<Event> getAllEvent(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    public void removeEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Event event1 = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + id));

        List<Event> conflicts = eventRepository.findConflictingEvents(
                event.getStartDate(),
                event.getEndDate(),
                event.getPlace()
        );

        conflicts.removeIf(e -> e.getId().equals(id));

        if (!conflicts.isEmpty()) {
            LocalDate newStart = event.getStartDate();
            LocalDate newEnd = event.getEndDate();

            while (!eventRepository.findConflictingEvents(newStart, newEnd, event.getPlace())
                    .stream()
                    .filter(e -> !e.getId().equals(id))
                    .toList()
                    .isEmpty()) {

                newStart = newStart.plusDays(1);
                newEnd = newEnd.plusDays(1);
            }

            throw new RuntimeException("⚠️ Conflict detected during modification. "
                    + "New proposed date : " + newStart + " - " + newEnd);
        }

        event1.setDescription(event.getDescription());
        event1.setTitle(event.getTitle());
        event1.setPlace(event.getPlace());
        event1.setStartDate(event.getStartDate());
        event1.setEndDate(event.getEndDate());
        event1.setCapacityMax(event.getCapacityMax());
        event1.setPrice(event.getPrice());

        return eventRepository.save(event1);
    }

    @Override
    public Event retrieveEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "event not found"));
    }

    @Override
    public int countAllEvent() {
        return eventRepository.countAllEvent();
    }

    public void exportEvents(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=events_dataset.csv");

        try (PrintWriter out = response.getWriter()) {
            out.println("title,description,place,startDate,endDate,capacityMax,price,nbParticipantsActuels,avg_feedback,popularity");

            for (Event e : eventRepository.findAll()) {
                double avgFeedback = e.getFeedBacks().isEmpty() ? 0.0 :
                        e.getFeedBacks().stream().mapToDouble(FeedBack::getRating).average().orElse(0.0);

                long confirmed = e.getReservations().stream()
                        .filter(r -> r.getStatus() == Status.CONFIRMED)
                        .mapToLong(Reservation::getNbPlace)
                        .sum();

                double occupancy = e.getCapacityMax() > 0 ? (double) confirmed / e.getCapacityMax() : 0.0;
                int popularity = occupancy >= 0.7 ? 1 : 0;

                out.printf("\"%s\",\"%s\",\"%s\",%s,%s,%d,%.2f,%d,%.2f,%d%n",
                        e.getTitle(), e.getDescription(), e.getPlace(),
                        e.getStartDate(), e.getEndDate(),
                        e.getCapacityMax(), e.getPrice(),
                        confirmed, avgFeedback, popularity);
            }

            out.flush();
        }
    }


}
