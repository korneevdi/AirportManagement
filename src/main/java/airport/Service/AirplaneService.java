package airport.Service;

import airport.entity.Airplane;
import airport.repository.AirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;

    public AirplaneService(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }
}
