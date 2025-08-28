package pidev.eventconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.eventconnect.entities.DiscountCode;

import java.util.Optional;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode,Long> {

    Optional<DiscountCode> findByCode(String code);
}
