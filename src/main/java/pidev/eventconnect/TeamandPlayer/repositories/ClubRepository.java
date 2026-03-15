package tn.esprit.tic.se.spr01.TeamandPlayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.tic.se.spr01.TeamandPlayer.entities.Club;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Integer> {

    @Query("SELECT c FROM Club c WHERE LOWER(c.clubName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Club> findClubsByName(@Param("name") String name);

}
