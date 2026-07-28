package airport.repository;

import airport.entity.AirportEmployeeEmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportEmployeeEmergencyContactRepository
        extends JpaRepository<AirportEmployeeEmergencyContact, Integer> {
}
