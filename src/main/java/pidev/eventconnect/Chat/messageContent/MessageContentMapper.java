package tn.esprit.tic.se.spr01.Chat.messageContent;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.tic.se.spr01.Chat.messageroom.MessageRoom;
import tn.esprit.tic.se.spr01.Chat.messageroom.MessageRoomRepository;
import tn.esprit.tic.se.spr01.UserManagement.DTO.UserWithAvatarDTO;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class MessageContentMapper {

    private final MessageRoomRepository messageRoomRepository;
    private final UserRepository userRepository;


    public MessageContentDTO toDTO(final MessageContent messageContent, final MessageContentDTO messageContentDTO) {
        messageContentDTO.setId(messageContent.getId());
        messageContentDTO.setContent(messageContent.getContent());
        messageContentDTO.setDateSent(messageContent.getDateSent());
        messageContentDTO.setMessageType(messageContent.getMessageType());
        messageContentDTO.setMessageRoomId(messageContent.getMessageRoom().getId());
        final UserWithAvatarDTO userWithAvatarDTO = new UserWithAvatarDTO();
        userWithAvatarDTO.setFirstname(messageContent.getUser().getFirstname());
        userWithAvatarDTO.setAvatarUrl(messageContent.getUser().getAvatarUrl());
        messageContentDTO.setSender(userWithAvatarDTO);
        return messageContentDTO;
    }


    public MessageContent toEntity(final MessageContentDTO messageContentDTO, final MessageContent messageContent) {
        messageContent.setId(messageContentDTO.getId());
        messageContent.setContent(messageContentDTO.getContent());
        messageContent.setDateSent(messageContentDTO.getDateSent());
        messageContent.setMessageType(messageContentDTO.getMessageType());
        final MessageRoom messageRoom = messageContentDTO.getMessageRoomId() == null ? null : messageRoomRepository.findById(messageContentDTO.getMessageRoomId())
                .orElseThrow(() -> new EntityNotFoundException("Message room not found"));
        messageContent.setMessageRoom(messageRoom);
        final User user = userRepository.findByFirstname(messageContentDTO.getSender().getFirstname())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        messageContent.setUser(user);
        return messageContent;
    }

}

