package tn.esprit.tic.se.spr01.Communication.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.Communication.DTO.ChatDto;
import tn.esprit.tic.se.spr01.Communication.Entities.Chat;
import tn.esprit.tic.se.spr01.Communication.Services.iChatService;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chats")
public class ChatController {
   iChatService chatService;

    @PostMapping("/addChat")
    @ResponseBody
    public Chat addChat(@Valid @RequestBody ChatDto dto) {
        return chatService.AddChat(dto);
    }

    @GetMapping("/getNotification/{id}")
    @ResponseBody
    public Chat getById(@PathVariable Long id) {
        return chatService.getChatById(id);
    }

    @GetMapping("/getAllChat")
    @ResponseBody
    public List<Chat> getAll(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        if (startDate != null && endDate != null) {
            return chatService.getChatByDateRange(startDate, endDate);
        }
        return chatService.getAllChat();
    }

    @PutMapping("/UpdateChat/{id}")
    @ResponseBody
    public Chat updateChat(@PathVariable Long id, @Valid @RequestBody ChatDto dto) {
        return chatService.updateChat(id, dto);
    }

    @DeleteMapping("/DeleteChat/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {chatService.deleteChat(id);}

}
