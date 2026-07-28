package airport.Service;

import airport.entity.AirportEmployee;
import airport.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportEmployeeService {

    private final AirportEmployeeRepository airportEmployeeRepository;

    private final AirportEmployeeRoleRepository airportEmployeeRoleRepository;

    private final SexRepository sexRepository;

    private final AirportEmployeeContactRepository airportEmployeeContactRepository;

    private final AirportEmployeeEmergencyContactRepository airportEmployeeEmergencyContactRepository;

    public AirportEmployeeService(
            AirportEmployeeRepository airportEmployeeRepository,
            AirportEmployeeRoleRepository airportEmployeeRoleRepository,
            SexRepository sexRepository,
            AirportEmployeeContactRepository airportEmployeeContactRepository,
            AirportEmployeeEmergencyContactRepository airportEmployeeEmergencyContactRepository) {
        this.airportEmployeeRepository = airportEmployeeRepository;
        this.airportEmployeeRoleRepository = airportEmployeeRoleRepository;
        this.sexRepository = sexRepository;
        this.airportEmployeeContactRepository = airportEmployeeContactRepository;
        this.airportEmployeeEmergencyContactRepository = airportEmployeeEmergencyContactRepository;
    }

    public List<AirportEmployee> getAllAirportEmployees() {
        return airportEmployeeRepository.findAll();
    }

    public AirportEmployee getAirportEmployee(Integer id) {
        return airportEmployeeRepository.findById(id)
                .orElseThrow();
    }
}
