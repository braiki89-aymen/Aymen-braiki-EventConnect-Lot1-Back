package tn.esprit.tic.se.spr01.Chat.messageroom;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/messagerooms")
@CrossOrigin(origins = "http://localhost:4200")

public class MessageRoomController {

    private final MessageRoomService messageRoomService;



    @GetMapping("/find/chat-room")
    public ResponseEntity<MessageRoomDTO> findMessageRoomByMembers(@RequestParam List<String> members) {
        return ResponseEntity.ok(messageRoomService.findMessageRoomByMembers(members));
    }


    @PostMapping("/create/chat-room")
    public ResponseEntity<MessageRoomDTO> createMessageRoom(@RequestParam final String currentUsername,
                                                            @RequestParam final List<String> members) {
        return ResponseEntity.ok(messageRoomService.createMessageRoom(currentUsername, members));
    }


    @GetMapping("/chat-rooms/{currentUsername}")
    public ResponseEntity<List<MessageRoomDTO>> findMessageRoomsByMemberWithMessages(@PathVariable final String currentUsername) {
        return ResponseEntity.ok(messageRoomService.findMessageRoomsByMemberWithMessages(currentUsername));
    }


    @GetMapping("/{roomId}/{currentUsername}")
    public ResponseEntity<MessageRoomDTO> findMessageRoomById(@PathVariable final UUID roomId,
                                                              @PathVariable final String currentUsername) {
        return ResponseEntity.ok(messageRoomService.findMessageRoomById(roomId, currentUsername));
    }


    @PostMapping("/update-group-name/{roomId}")
    public ResponseEntity<MessageRoomDTO> updateMessageRoomName(@PathVariable final UUID roomId,
                                                                @RequestBody final String name) {
        return ResponseEntity.ok(messageRoomService.updateMessageRoomName(roomId, name));
    }

}

