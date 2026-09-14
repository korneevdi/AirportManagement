package airport.service;

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

    public FlightStatus getFlightStatusById(Integer id) {
        return flightStatusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Status not found"));
    }

    public FlightStatus saveFlightStatus(FlightStatus status) {
        return flightStatusRepository.save(status);
    }

    public void deleteFlightStatus(Integer id) {
        flightStatusRepository.deleteById(id);
    }
}
