package tn.esprit.tic.se.spr01.Communication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.se.spr01.Communication.Entities.Notification;
import tn.esprit.tic.se.spr01.Communication.Entities.StatusNotif;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByStatus(StatusNotif status);
}