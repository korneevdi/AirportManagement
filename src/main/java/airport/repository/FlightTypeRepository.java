package airport.repository;

import airport.entity.FlightType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightTypeRepository extends JpaRepository<FlightType, Integer> {
}
