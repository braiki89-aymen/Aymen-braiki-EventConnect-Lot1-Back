package tn.esprit.tic.se.spr01.Chat.messageContent;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.Chat.messageroommember.MessageRoomMemberService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/messagecontents")
@CrossOrigin(origins = "http://localhost:4200")

@RequiredArgsConstructor
public class MessageContentController {

    private final MessageContentService messageContentService;
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageRoomMemberService messageRoomMemberService;


    @MessageMapping("/send-message")
    public void sendMessage(@Payload final MessageContentDTO messageContentDTO) {
        final MessageContentDTO savedMessage = messageContentService.save(messageContentDTO);
        List<String> members = messageRoomMemberService.findMemberFirstnamesByRoomId(savedMessage.getMessageRoomId());
        members.removeIf(member -> member.equals(savedMessage.getSender().getFirstname()));
        members.forEach(member -> {
            messagingTemplate.convertAndSendToUser(
                    member,
                    "/queue/messages",
                    savedMessage
            );
        });
    }


    @GetMapping("/{roomId}")
    public List<MessageContentDTO> getMessagesByRoomId(@PathVariable UUID roomId) {
        return messageContentService.findByMessageRoomId(roomId);
    }

}

