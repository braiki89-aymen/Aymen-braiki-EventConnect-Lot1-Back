package tn.esprit.tic.se.spr01.Chat.messageContent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageContentService {

    private final MessageContentRepository messageContentRepository;
    private final MessageContentMapper messageContentMapper;


    public MessageContentDTO save(MessageContentDTO messageContentDTO) {
        MessageContent messageContent = new MessageContent();
        messageContentMapper.toEntity(messageContentDTO, messageContent);
        return messageContentMapper.toDTO(messageContentRepository.save(messageContent), new MessageContentDTO());
    }


    public List<MessageContentDTO> findByMessageRoomId(final UUID roomId) {
        return messageContentRepository.findByMessageRoomIdOrderByDateSent(roomId)
                .stream()
                .map(messageContent -> messageContentMapper.toDTO(messageContent, new MessageContentDTO()))
                .collect(Collectors.toList());
    }

}

