package airport.Service;

import airport.entity.Flight;
import airport.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository repository) {
        this.flightRepository = repository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlight(Integer id) {
        return flightRepository.findById(id)
                .orElseThrow();
    }
}
