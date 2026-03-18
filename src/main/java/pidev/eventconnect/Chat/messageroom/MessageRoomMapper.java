package tn.esprit.tic.se.spr01.Chat.messageroom;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.tic.se.spr01.Chat.messageContent.MessageContentDTO;
import tn.esprit.tic.se.spr01.Chat.messageContent.MessageContentMapper;
import tn.esprit.tic.se.spr01.Chat.messageContent.MessageContentRepository;
import tn.esprit.tic.se.spr01.Chat.messageroommember.MessageRoomMemberRepository;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class MessageRoomMapper {

    private final MessageContentRepository messageContentRepository;
    private final MessageContentMapper messageContentMapper;
    private final MessageRoomMemberRepository messageRoomMemberRepository;
    private final UserRepository userRepository;


    public MessageRoomDTO toDTO(final MessageRoom messageRoom, final MessageRoomDTO messageRoomDTO) {
        messageRoomDTO.setId(messageRoom.getId());
        // MessageRoom name
        if(messageRoom.getName() == null) {
            messageRoomDTO.setName(messageRoomMemberRepository.findMemberFirstnamesByRoomId(messageRoom.getId())
                    .stream()
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElse(""));
        } else {
            messageRoomDTO.setName(messageRoom.getName());
        }
        messageRoomDTO.setIsGroup(messageRoom.getIsGroup());
        messageRoomDTO.setCreatedAt(messageRoom.getCreatedAt());
        messageRoomDTO.setCreatedById(messageRoom.getCreatedBy().getFirstname());
        messageContentRepository.findTopByMessageRoomIdOrderByDateSentDesc(messageRoom.getId())
                .ifPresent(lastMessage -> messageRoomDTO.setLastMessage(messageContentMapper.toDTO(lastMessage, new MessageContentDTO())));
        return messageRoomDTO;
    }


    public MessageRoomDTO toDTO(final String currentUsername, final MessageRoom messageRoom, final MessageRoomDTO messageRoomDTO) {
        messageRoomDTO.setId(messageRoom.getId());
        // MessageRoom name
        if(messageRoom.getName() == null) {
            messageRoomDTO.setName(messageRoomMemberRepository.findMemberFirstnamesByRoomId(messageRoom.getId())
                    .stream()
                    .filter(firstname -> (messageRoom.getIsGroup() || !firstname.equals(currentUsername)))
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElse(""));
        } else {
            messageRoomDTO.setName(messageRoom.getName());
        }
        // MessageRoom avatar
        if(!messageRoom.getIsGroup()) {
            messageRoomDTO.setAvatarUrl(userRepository.findByFirstname(messageRoomDTO.getName())
                    .map(User::getAvatarUrl)
                    .orElse(messageRoom.getName()));
        }
        messageRoomDTO.setIsGroup(messageRoom.getIsGroup());
        messageRoomDTO.setCreatedAt(messageRoom.getCreatedAt());
        messageRoomDTO.setCreatedById(messageRoom.getCreatedBy().getFirstname());
        messageContentRepository.findTopByMessageRoomIdOrderByDateSentDesc(messageRoom.getId())
                .ifPresent(lastMessage -> messageRoomDTO.setLastMessage(messageContentMapper.toDTO(lastMessage, new MessageContentDTO())));
        messageRoomDTO.setUnSeenCount(messageContentRepository.countUnseenMessagesByRoomIdAndFirstname(messageRoom.getId(), currentUsername));
        return messageRoomDTO;
    }


    public MessageRoom toEntity(final MessageRoomDTO messageRoomDTO, final MessageRoom messageRoom) {
        messageRoom.setId(messageRoomDTO.getId());
        messageRoom.setName(messageRoomDTO.getName());
        messageRoom.setIsGroup(messageRoomDTO.getIsGroup());
        messageRoom.setCreatedAt(messageRoomDTO.getCreatedAt());
        return messageRoom;
    }

}

