package airport.repository;

import airport.entity.AirportEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportEmployeeRepository extends JpaRepository<AirportEmployee, Integer> {

    List<AirportEmployee> findByRoleName(String roleName);
}
