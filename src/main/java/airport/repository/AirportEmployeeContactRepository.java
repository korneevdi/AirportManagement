package airport.repository;

import airport.entity.AirportEmployeeContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportEmployeeContactRepository extends JpaRepository<AirportEmployeeContact, Integer> {
}
