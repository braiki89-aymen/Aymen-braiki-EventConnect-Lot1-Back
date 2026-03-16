package tn.esprit.tic.se.spr01.Chat.messageroom;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, UUID> {
    @Query("SELECT mr " +
            "FROM MessageRoom mr " +
            "JOIN mr.members m " +
            "WHERE m.user.firstname IN :members " +
            "GROUP BY mr.id " +
            "HAVING COUNT(m.user.firstname) = :size " +
            "AND SIZE(m) = :size")
    Optional<MessageRoom> findMessageRoomByExactMembers(@Param("members") List<String> members, @Param("size") int size);

    @Query("SELECT mr " +
            "FROM MessageRoom mr " +
            "JOIN mr.members mrm " +
            "JOIN mr.messageContents mc " +
            "WHERE mrm.user.firstname = :firstname " +
            "GROUP BY mr " +
            "HAVING COUNT(mc) > 0 " +
            "ORDER BY MAX(mc.dateSent) DESC")
    List<MessageRoom> findMessageRoomsByMemberWithMessagesOrderByLastMessageDateSentDesc(@Param("firstname") String firstname);
}





