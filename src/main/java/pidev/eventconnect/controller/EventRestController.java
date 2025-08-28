package pidev.eventconnect.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pidev.eventconnect.entities.Event;
import pidev.eventconnect.services.EventServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
@CrossOrigin(origins = "*")
public class EventRestController {
    @Autowired
    EventServiceImpl eventService;
    @PostMapping("/addEvent")
    public ResponseEntity<?> addEvent(@RequestBody Event event) {
        try {
            Event saved = eventService.addEvent(event);
            return ResponseEntity.ok(saved);
        } catch (ResponseStatusException e) {

            Map<String, String> body = new HashMap<>();
            body.put("message", e.getReason());
            return ResponseEntity.status(e.getStatusCode()).body(body);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }





    @GetMapping ("/AllEvents")
    public Page<Event> getAllEvents (@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "3") int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return eventService.getAllEvent(pageable);
    }

    @GetMapping("/retrieve-event/{id}")
    public Event getEvent (@PathVariable ("id") Long id){
        return eventService.retrieveEvent(id);
    }

    @DeleteMapping("/remove-event/{id}")
    public void deleteEvent(@PathVariable("id") Long id){
        eventService.removeEvent(id);

    }

    @PutMapping ("/update-event/{id}")
    public ResponseEntity<Event> updateEvent (@Valid @RequestBody Event event,
                                              @PathVariable ("id") Long id){
        try {
            Event event1 = eventService.updateEvent(id,event);
            return ResponseEntity.ok(event1);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }
    @GetMapping("/countAllEvents")
    public int countAllEvents (){
        return eventService.countAllEvent();

    }
}
