package pi_4se3.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi_4se3.backend.entities.MedicalStaff;

import java.util.List;

public interface MedicalStaffRepository extends JpaRepository<MedicalStaff,Long> {

    List<MedicalStaff> findAllBy();
}
