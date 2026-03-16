package tn.esprit.tic.se.spr01.Chat.messageroom;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tic.se.spr01.Chat.messageContent.MessageContent;
import tn.esprit.tic.se.spr01.Chat.messageroommember.MessageRoomMember;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageRoomService {

    private final MessageRoomRepository messageRoomRepository;
    private final MessageRoomMapper messageRoomMapper;
    private final UserRepository userRepository;


    public MessageRoomDTO findMessageRoomByMembers(final List<String> members) {
        return messageRoomRepository.findMessageRoomByExactMembers(members, members.size())
                .map(messageRoom -> messageRoomMapper.toDTO(messageRoom, new MessageRoomDTO()))
                .orElse(null);
    }


    @Transactional
    protected MessageRoomDTO createMessageRoom(final String currentUsername, List<String> memberUsernames) {
        List<User> users = userRepository.findAllByFirstnameIn(memberUsernames);

        MessageRoom messageRoom = MessageRoom.builder()
                .isGroup(memberUsernames.size() > 2)
                .createdBy(userRepository.findByFirstname(currentUsername).orElseThrow())
                .members(new ArrayList<>())
                .build();

        users.forEach(user -> {
            if (!user.getFirstname().equals(currentUsername)) {
                messageRoom.getMembers().add(createMessageRoomMember(user, messageRoom, false));
            }
        });

        messageRoom.getMembers().add(
                createMessageRoomMember(userRepository.findByFirstname(currentUsername).orElseThrow(), messageRoom, true)
        );

        MessageRoom savedMessageRoom = messageRoomRepository.save(messageRoom);

        return messageRoomMapper.toDTO(currentUsername, savedMessageRoom, new MessageRoomDTO());
    }


    private MessageRoomMember createMessageRoomMember(User user, MessageRoom messageRoom, boolean isAdmin) {
        return MessageRoomMember.builder()
                .user(user)
                .messageRoom(messageRoom)
                .isAdmin(isAdmin)
                .lastSeen(LocalDateTime.now())
                .build();
    }


    public List<MessageRoomDTO> findMessageRoomsByMemberWithMessages(final String currentUsername) {
        return messageRoomRepository.findMessageRoomsByMemberWithMessagesOrderByLastMessageDateSentDesc(currentUsername)
                .stream()
                .map(messageRoom -> messageRoomMapper.toDTO(currentUsername, messageRoom, new MessageRoomDTO()))
                .toList();
    }


    public MessageRoomDTO findMessageRoomById(final UUID roomId, final String currentUsername) {
        return messageRoomRepository.findById(roomId)
                .map(messageRoom -> messageRoomMapper.toDTO(currentUsername, messageRoom, new MessageRoomDTO()))
                .orElseThrow();
    }


    public MessageRoomDTO updateMessageRoomName(final UUID roomId, final String name) {
        MessageRoom messageRoom = messageRoomRepository.findById(roomId).orElseThrow(EntityNotFoundException::new);
        messageRoom.setName(name);
        return messageRoomMapper.toDTO(messageRoomRepository.save(messageRoom), new MessageRoomDTO());
    }

}


