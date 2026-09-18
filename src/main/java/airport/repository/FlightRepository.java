package airport.repository;

import airport.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findAllByOrderByFlightNumberAsc();
}
