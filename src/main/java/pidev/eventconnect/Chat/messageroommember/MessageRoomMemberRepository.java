package tn.esprit.tic.se.spr01.Chat.messageroommember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface MessageRoomMemberRepository extends JpaRepository<MessageRoomMember, MessageRoomMemberKey> {

    @Query("SELECT m.user.firstname FROM MessageRoomMember m WHERE m.messageRoom.id = :roomId")
    List<String> findMemberFirstnamesByRoomId(UUID roomId);

    List<MessageRoomMember> findByMessageRoomId(UUID roomId);

    MessageRoomMember findByMessageRoomIdAndUserFirstname(UUID messageRoomId, String firstname);

}
