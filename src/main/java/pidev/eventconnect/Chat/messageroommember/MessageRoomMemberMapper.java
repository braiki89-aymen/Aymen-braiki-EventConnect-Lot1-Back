package tn.esprit.tic.se.spr01.Chat.messageroommember;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.tic.se.spr01.Chat.messageroom.MessageRoom;
import tn.esprit.tic.se.spr01.Chat.messageroom.MessageRoomRepository;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class MessageRoomMemberMapper {

    private final MessageRoomRepository messageRoomRepository;
    private final UserRepository userRepository;


    public MessageRoomMemberDTO toDTO(final MessageRoomMember messageRoomMember, final MessageRoomMemberDTO messageRoomMemberDTO) {
        messageRoomMemberDTO.setMessageRoomId(messageRoomMember.getMessageRoom().getId());
        messageRoomMemberDTO.setFirstname(messageRoomMember.getUser().getFirstname());
        messageRoomMemberDTO.setAvatarUrl(messageRoomMember.getUser().getAvatarUrl());
        messageRoomMemberDTO.setIsAdmin(messageRoomMember.getIsAdmin());
        messageRoomMemberDTO.setLastSeen(messageRoomMember.getLastSeen());
        messageRoomMemberDTO.setLastLogin(messageRoomMember.getUser().getLastLogin());
        return messageRoomMemberDTO;
    }


    public MessageRoomMember toEntity(final MessageRoomMemberDTO messageRoomMemberDTO, final MessageRoomMember messageRoomMember) {
        messageRoomMember.setMessageRoom(messageRoomMemberDTO.getMessageRoomId() == null ? null : messageRoomRepository.findById(messageRoomMemberDTO.getMessageRoomId())
                .orElseThrow(() -> new EntityNotFoundException("Message room not found")));
        messageRoomMember.setUser(messageRoomMemberDTO.getFirstname() == null ? null : userRepository.findByFirstname(messageRoomMemberDTO.getFirstname())
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
        messageRoomMember.setIsAdmin(messageRoomMemberDTO.getIsAdmin());
        messageRoomMember.setLastSeen(messageRoomMemberDTO.getLastSeen());
        return messageRoomMember;
    }
}

