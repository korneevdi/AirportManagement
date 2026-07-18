package airport.repository;

import airport.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {
}
