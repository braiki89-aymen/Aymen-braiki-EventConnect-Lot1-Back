package tn.esprit.tic.se.spr01.Communication.Services;

import tn.esprit.tic.se.spr01.Communication.DTO.NotificationDto;
import tn.esprit.tic.se.spr01.Communication.Entities.Notification;
import tn.esprit.tic.se.spr01.Communication.Entities.StatusNotif;

import java.util.List;

public interface iNotificationService {

    Notification addNotification(NotificationDto dto);
    Notification getNotificationById(Long id);
    List<Notification> getAllNotification();
    List<Notification> getByStatus(StatusNotif status);
    Notification updateNotification(Long id, NotificationDto dto);
    void deleteNotification(Long id);
}
