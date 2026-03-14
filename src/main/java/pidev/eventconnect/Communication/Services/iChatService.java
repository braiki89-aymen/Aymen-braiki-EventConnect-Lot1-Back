package tn.esprit.tic.se.spr01.Communication.Services;

import tn.esprit.tic.se.spr01.Communication.DTO.ChatDto;
import tn.esprit.tic.se.spr01.Communication.Entities.Chat;

import java.time.LocalDate;
import java.util.List;

public interface iChatService {

    Chat AddChat(ChatDto dto);
    Chat getChatById(Long id);
    List<Chat> getAllChat();
    List<Chat> getChatByDateRange(LocalDate startDate, LocalDate endDate);
    Chat updateChat(Long id, ChatDto dto);
    void deleteChat(Long id);
}
