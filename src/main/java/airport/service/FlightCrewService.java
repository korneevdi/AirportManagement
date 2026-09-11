package airport.service;

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

    public FlightCrew getFlightCrewById(Integer id) {
        return flightCrewRepository.findById(id)
                .orElseThrow();
    }

    public void saveFlightCrew(FlightCrew crew) {
        flightCrewRepository.save(crew);
    }
}
