package tn.esprit.tic.se.spr01.Communication.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.Communication.DTO.NotificationDto;
import tn.esprit.tic.se.spr01.Communication.Entities.Notification;
import tn.esprit.tic.se.spr01.Communication.Entities.StatusNotif;
import tn.esprit.tic.se.spr01.Communication.Repositories.NotificationRepository;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.UserRepository;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class NotificationService implements iNotificationService {

    NotificationRepository notificationRepository;
    UserRepository userRepository;

    public Notification addNotification(NotificationDto dto) {
        Notification notification = new Notification();
        notification.setContent(dto.content());
        notification.setStatus(dto.status());

        return notificationRepository.save(notification);
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with id: " + id));
    }

    public List<Notification> getAllNotification() {
        return notificationRepository.findAll();
    }

    public List<Notification> getByStatus(StatusNotif status) {
        return notificationRepository.findByStatus(status);
    }

    public Notification updateNotification(Long id, NotificationDto dto) {
        Notification existingNotification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with id: " + id));

        existingNotification.setContent(dto.content());
        existingNotification.setStatus(dto.status());

        return notificationRepository.save(existingNotification);
    }

    public void deleteNotification(Long id) {

        notificationRepository.deleteById(id);
    }
}
