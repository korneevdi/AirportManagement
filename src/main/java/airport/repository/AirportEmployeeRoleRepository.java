package airport.repository;

import airport.entity.AirportEmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportEmployeeRoleRepository extends JpaRepository<AirportEmployeeRole, Integer> {

    List<AirportEmployeeRole> findAllByOrderByNameAsc();
}
