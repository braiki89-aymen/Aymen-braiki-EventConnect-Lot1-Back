package tn.esprit.tic.se.spr01.UserManagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Coach;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findSenderById(Long id);
    User findReceiverById(Long id);
    Optional <User> findByEmail(String email);

    boolean existsByEmail(String email);
    Optional<User> findByFirstname(String firstname);


    List<User> findAllByStatus(Status status);
    List<User> findTop20ByFirstnameContaining(String firstname);
    List<User> findAllByFirstnameIn(List<String> firstnames);

    @Query("SELECT u FROM User u WHERE u.firstname LIKE %:firstname% AND u.firstname NOT IN :firstnames")
    List<User> findTop20ByFirstnameContainingAndFirstnameNotIn(String firstname, List<String> firstnames);

    List<User> findTop20ByFirstnameContainingIgnoreCaseAndIdNotIn(
            String firstname,
            List<Long> excludedIds
    );

    List<User> findTop20ByFirstnameContainingIgnoreCase(String firstname);



    Optional<Coach> findCoachById(Long id);


    @Query("SELECT u FROM User u WHERE TYPE(u) = Coach")
    List<Coach> findAllCoaches();

}