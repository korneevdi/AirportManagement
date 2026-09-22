package airport.repository;

import airport.entity.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightStatusRepository extends JpaRepository<FlightStatus, Integer> {

    List<FlightStatus> findAllByOrderByNameAsc();

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
