package airport.Service;

import airport.entity.FlightType;
import airport.repository.FlightTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightTypeService {

    private final FlightTypeRepository flightTypeRepository;

    public FlightTypeService(FlightTypeRepository repository) {
        this.flightTypeRepository = repository;
    }

    public List<FlightType> getAllFlightTypes() {
        return flightTypeRepository.findAll();
    }
}
