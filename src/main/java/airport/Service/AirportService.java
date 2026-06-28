package airport.Service;

import airport.entity.Airport;
import airport.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository repository) {
        this.airportRepository = repository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
}
