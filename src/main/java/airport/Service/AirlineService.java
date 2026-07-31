package airport.Service;

import airport.entity.Airline;
import airport.repository.AirlineContactRepository;
import airport.repository.AirlineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {

    private final AirlineRepository airlineRepository;
    private final AirlineContactRepository airlineContactRepository;

    public AirlineService(
            AirlineRepository airlineRepository,
            AirlineContactRepository airlineContactRepository) {
        this.airlineRepository = airlineRepository;
        this.airlineContactRepository = airlineContactRepository;
    }

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public Airline getAirline(Integer id) {
        return airlineRepository.findById(id)
                .orElseThrow();
    }
}
