package pi_4se3.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pi_4se3.backend.entities.Player;
import pi_4se3.backend.entities.User;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,Long> {

    List<Player> findAllBy();

  /* @Query("SELECT p FROM Player p LEFT JOIN p.healthReport h WHERE h IS NULL")
    List<Player> findPlayersWithoutHealthReport();
*/

}
