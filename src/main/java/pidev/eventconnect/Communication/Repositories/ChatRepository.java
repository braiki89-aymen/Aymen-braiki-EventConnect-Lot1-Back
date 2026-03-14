package tn.esprit.tic.se.spr01.Communication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.se.spr01.Communication.Entities.Chat;

import java.time.LocalDate;
import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {


    List<Chat> findByDateBetween(LocalDate startDate, LocalDate endDate);
}