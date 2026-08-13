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

    public Airport getAirportById(Integer id) {
        return  airportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airport not found"));
    }

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public void deleteAirport(Integer id) {
        airportRepository.deleteById(id);
    }
}
