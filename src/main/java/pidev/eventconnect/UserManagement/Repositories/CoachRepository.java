package tn.esprit.tic.se.spr01.UserManagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Coach;

import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    @Query("SELECT c FROM Coach c WHERE c.id = :id")
    Optional<Coach> findById(@Param("id") Integer id);
}