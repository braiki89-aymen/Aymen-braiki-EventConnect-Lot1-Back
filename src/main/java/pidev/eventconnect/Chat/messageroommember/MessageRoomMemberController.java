package tn.esprit.tic.se.spr01.Chat.messageroommember;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/messageroommembers")
@CrossOrigin(origins = "http://localhost:4200")

@RequiredArgsConstructor
public class MessageRoomMemberController {

    private final MessageRoomMemberService messageRoomMemberService;


    @GetMapping("/{roomId}")
    public ResponseEntity<List<MessageRoomMemberDTO>> getMembersByRoomId(@PathVariable UUID roomId) {
        return ResponseEntity.ok(messageRoomMemberService.findByMessageRoomId(roomId));
    }


    @PostMapping("/update-last-seen/{roomId}/{memberId}")
    public ResponseEntity<MessageRoomMemberDTO> updateLastSeen(@PathVariable String memberId,
                                                               @PathVariable UUID roomId) {
        return ResponseEntity.ok(messageRoomMemberService.updateLastSeen(memberId, roomId));
    }


    @PostMapping("/{roomId}")
    public ResponseEntity<List<MessageRoomMemberDTO>> addMember(@PathVariable UUID roomId,
                                                                @RequestBody List<MessageRoomMemberDTO> messageRoomMemberDTO) {
        return ResponseEntity.ok(messageRoomMemberService.addMembers(roomId, messageRoomMemberDTO));
    }


    @PostMapping("/make-admin/{roomId}/{memberId}")
    public ResponseEntity<MessageRoomMemberDTO> makeAdmin(@PathVariable UUID roomId,
                                                          @PathVariable String memberId) {
        return ResponseEntity.ok(messageRoomMemberService.makeAdmin(roomId, memberId));
    }


    @PostMapping("/remove-admin/{roomId}/{memberId}")
    public ResponseEntity<MessageRoomMemberDTO> removeAdmin(@PathVariable UUID roomId,
                                                            @PathVariable String memberId) {
        return ResponseEntity.ok(messageRoomMemberService.removeAdmin(roomId, memberId));
    }


    @DeleteMapping("/remove-member/{roomId}/{memberId}")
    public ResponseEntity<Void> removeMember(@PathVariable UUID roomId,
                                             @PathVariable String memberId) {
        messageRoomMemberService.removeMember(roomId, memberId);
        return ResponseEntity.ok().build();
    }

}

