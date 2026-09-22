package airport.service;

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
        return airportEmployeeRoleRepository.findAllByOrderByNameAsc();
    }

    public AirportEmployeeRole getAirportEmployeeRoleById(Integer id) {
        return airportEmployeeRoleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airport employee role not found"));
    }

    public boolean existsByName(String name) {
        return airportEmployeeRoleRepository.existsByName(name);
    }

    public boolean existsByNameAndIdNot(String name, Integer id) {
        return airportEmployeeRoleRepository.existsByNameAndIdNot(name, id);
    }

    public AirportEmployeeRole saveAirportEmployeeRole(AirportEmployeeRole role) {
        return airportEmployeeRoleRepository.save(role);
    }

    public void deleteAirportEmployeeRole(Integer id) {
        airportEmployeeRoleRepository.deleteById(id);
    }
}
