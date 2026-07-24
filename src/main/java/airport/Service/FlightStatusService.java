package airport.Service;

import airport.entity.FlightStatus;
import airport.repository.FlightStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightStatusService {

    private final FlightStatusRepository flightStatusRepository;

    public FlightStatusService(FlightStatusRepository repository) {
        this.flightStatusRepository = repository;
    }

    public List<FlightStatus> getAllFlightStatuses() {
        return flightStatusRepository.findAll();
    }
}
