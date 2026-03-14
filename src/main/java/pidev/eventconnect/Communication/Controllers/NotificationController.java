package tn.esprit.tic.se.spr01.Communication.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.Communication.DTO.NotificationDto;
import tn.esprit.tic.se.spr01.Communication.Entities.Notification;
import tn.esprit.tic.se.spr01.Communication.Entities.StatusNotif;
import tn.esprit.tic.se.spr01.Communication.Services.iNotificationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    iNotificationService notificationService;

    @PostMapping("/addNotification")
    @ResponseBody
    public Notification addNotification(@Valid @RequestBody NotificationDto dto) {
        return notificationService.addNotification(dto);
    }

    @GetMapping("/getNotification/{id}")
    @ResponseBody
    public Notification getById(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }

    @GetMapping("/getAllNotification")
    @ResponseBody
    public List<Notification> getAll(@RequestParam(required = false) StatusNotif status) {
        if (status != null) {
            return notificationService.getByStatus(status);
        }
        return notificationService.getAllNotification();
    }

    @PutMapping("/UpdateNotification/{id}")
    @ResponseBody
    public Notification updateNotification(@PathVariable Long id, @Valid @RequestBody NotificationDto dto) {
        return notificationService.updateNotification(id, dto);
    }

    @DeleteMapping("/DeleteNotification/{id}")
    @ResponseBody
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }

}
