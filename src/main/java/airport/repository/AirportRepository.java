package airport.repository;

import airport.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

    List<Airport> findAllByOrderByNameAsc();
}
