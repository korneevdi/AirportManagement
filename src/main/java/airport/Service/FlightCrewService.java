package airport.Service;

import airport.entity.FlightCrew;
import airport.repository.FlightCrewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightCrewService {

    private final FlightCrewRepository flightCrewRepository;

    public FlightCrewService(FlightCrewRepository repository) {
        this.flightCrewRepository = repository;
    }

    public List<FlightCrew> getAllFlightCrews() {
        return flightCrewRepository.findAll();
    }
}
