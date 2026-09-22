package airport.repository;

import airport.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {

    List<Airline> findAllByOrderByNameAsc();
}
