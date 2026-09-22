package airport.repository;

import airport.entity.FlightCrew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightCrewRepository extends JpaRepository<FlightCrew, Integer> {

    List<FlightCrew> findAllByOrderByLastNameAsc();
}
