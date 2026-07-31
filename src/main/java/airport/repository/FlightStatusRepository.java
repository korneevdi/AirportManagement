package airport.repository;

import airport.entity.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightStatusRepository extends JpaRepository<FlightStatus, Integer> {
}
