package tn.esprit.tic.se.spr01.Chat.messageContent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageContentRepository extends JpaRepository<MessageContent, UUID> {
    Optional<MessageContent> findTopByMessageRoomIdOrderByDateSentDesc(UUID messageRoomId);
    List<MessageContent> findByMessageRoomIdOrderByDateSent(UUID messageRoomId);

    @Query("SELECT COUNT(mc) " +
            "FROM MessageContent mc " +
            "JOIN MessageRoomMember mrm " +
            "ON mc.messageRoom.id = mrm.messageRoom.id " +
            "WHERE mc.messageRoom.id = :roomId " +
            "AND mc.dateSent > mrm.lastSeen " +
            "AND mrm.user.firstname = :firstname")
    Long countUnseenMessagesByRoomIdAndFirstname(@Param("roomId") UUID roomId, @Param("firstname") String firstname);
}
