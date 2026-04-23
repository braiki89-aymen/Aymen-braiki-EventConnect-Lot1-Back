package tn.esprit.tic.se.spr01.UserManagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tic.se.spr01.UserManagement.Entities.MedicalStaff;

import java.util.List;

@Repository
public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long> {
    List<MedicalStaff> findAllBy();
}