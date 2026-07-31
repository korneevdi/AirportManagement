package airport.repository;

import airport.entity.AirportEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportEmployeeRepository extends JpaRepository<AirportEmployee, Integer> {
}
