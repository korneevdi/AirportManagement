package airport.Service;

import airport.entity.AirportEmployeeRole;
import airport.repository.AirportEmployeeRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportEmployeeRoleService {

    private final AirportEmployeeRoleRepository airportEmployeeRoleRepository;

    public AirportEmployeeRoleService(AirportEmployeeRoleRepository repository) {
        this.airportEmployeeRoleRepository = repository;
    }

    public List<AirportEmployeeRole> getAllAirportEmployeeRoles() {
        return airportEmployeeRoleRepository.findAll();
    }
}
