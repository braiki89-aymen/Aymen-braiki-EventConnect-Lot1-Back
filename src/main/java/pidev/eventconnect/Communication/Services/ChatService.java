package tn.esprit.tic.se.spr01.Communication.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.Communication.DTO.ChatDto;
import tn.esprit.tic.se.spr01.Communication.Entities.Chat;
import tn.esprit.tic.se.spr01.Communication.Repositories.ChatRepository;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.UserRepository;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Service
@Slf4j
@AllArgsConstructor
public class ChatService implements iChatService {

    ChatRepository chatRepository;
    UserRepository userRepository;
    public Chat AddChat(ChatDto dto) {
        User sender = userRepository.findSenderById(dto.idSender());

        User receiver = userRepository.findReceiverById(dto.idReceiver());

        Chat chat = new Chat();
        chat.setIdSender(dto.idSender());
        chat.setIdReceiver(dto.idReceiver());
        chat.setMessage(dto.message());
        chat.setDate(dto.date());

        chat.setUserSet(Set.of(sender, receiver));

        return chatRepository.save(chat);
    }

    public Chat getChatById(Long id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chat not found with id: " + id));
    }

    public List<Chat> getAllChat() {
        return chatRepository.findAll();
    }

    public List<Chat> getChatByDateRange(LocalDate startDate, LocalDate endDate) {
        return chatRepository.findByDateBetween(startDate, endDate);
    }

    public Chat updateChat(Long id, ChatDto dto) {
        Chat existingChat = chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chat not found with id: " + id));

        existingChat.setIdSender(dto.idSender());
        existingChat.setIdReceiver(dto.idReceiver());
        existingChat.setMessage(dto.message());
        existingChat.setDate(dto.date());

        return chatRepository.save(existingChat);
    }

    public void deleteChat(Long id) {
        if (!chatRepository.existsById(id)) {
            throw new EntityNotFoundException("Chat not found with id: " + id);
        }
        chatRepository.deleteById(id);
    }
}
