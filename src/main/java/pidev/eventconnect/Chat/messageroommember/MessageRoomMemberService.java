package tn.esprit.tic.se.spr01.Chat.messageroommember;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.Chat.messageroom.MessageRoomRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageRoomMemberService {

    private final MessageRoomMemberRepository messageRoomMemberRepository;
    private final MessageRoomMemberMapper messageRoomMemberMapper;
    private final MessageRoomRepository messageRoomRepository;


    public List<String> findMemberFirstnamesByRoomId(final UUID roomId) {
        return new ArrayList<>(messageRoomMemberRepository.findMemberFirstnamesByRoomId(roomId));
    }


    public List<MessageRoomMemberDTO> findByMessageRoomId(UUID roomId) {
        return messageRoomMemberRepository.findByMessageRoomId(roomId)
                .stream()
                .map(messageRoomMember -> messageRoomMemberMapper.toDTO(messageRoomMember, new MessageRoomMemberDTO()))
                .collect(Collectors.toList());
    }


    public MessageRoomMemberDTO updateLastSeen(final String memberId, final UUID roomId) {
        MessageRoomMember messageRoomMember = messageRoomMemberRepository.findByMessageRoomIdAndUserFirstname(roomId, memberId);
        LocalDateTime now = LocalDateTime.now();
        messageRoomMember.setLastSeen(now);
        messageRoomMemberRepository.save(messageRoomMember);
        return messageRoomMemberMapper.toDTO(messageRoomMember, new MessageRoomMemberDTO());
    }


    public List<MessageRoomMemberDTO> addMembers(final UUID roomId, final List<MessageRoomMemberDTO> messageRoomMemberDTOs) {
        List<MessageRoomMember> messageRoomMembers = messageRoomMemberDTOs.stream()
                .map(messageRoomMemberDTO -> {
                    messageRoomMemberDTO.setMessageRoomId(roomId);
                    return messageRoomMemberMapper.toEntity(messageRoomMemberDTO, new MessageRoomMember());
                })
                .toList();

        messageRoomMemberRepository.saveAll(messageRoomMembers);
        return messageRoomMembers.stream()
                .map(messageRoomMember -> messageRoomMemberMapper.toDTO(messageRoomMember, new MessageRoomMemberDTO()))
                .collect(Collectors.toList());
    }


    public MessageRoomMemberDTO makeAdmin(final UUID roomId, final String memberId) {
        MessageRoomMember messageRoomMember = messageRoomMemberRepository.findByMessageRoomIdAndUserFirstname(roomId, memberId);
        messageRoomMember.setIsAdmin(true);
        messageRoomMemberRepository.save(messageRoomMember);
        return messageRoomMemberMapper.toDTO(messageRoomMember, new MessageRoomMemberDTO());
    }


    public MessageRoomMemberDTO removeAdmin(final UUID roomId, final String memberId) {
        MessageRoomMember messageRoomMember = messageRoomMemberRepository.findByMessageRoomIdAndUserFirstname(roomId, memberId);
        messageRoomMember.setIsAdmin(false);
        messageRoomMemberRepository.save(messageRoomMember);
        return messageRoomMemberMapper.toDTO(messageRoomMember, new MessageRoomMemberDTO());
    }


    public void removeMember(final UUID roomId, final String memberId) {
        MessageRoomMember messageRoomMember = messageRoomMemberRepository.findByMessageRoomIdAndUserFirstname(roomId, memberId);
        messageRoomMemberRepository.delete(messageRoomMember);
    }

}
