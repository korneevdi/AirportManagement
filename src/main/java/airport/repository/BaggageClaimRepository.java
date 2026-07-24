package airport.repository;

import airport.entity.BaggageClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaggageClaimRepository extends JpaRepository<BaggageClaim, Integer> {
}
