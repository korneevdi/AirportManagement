package airport.Service;

import airport.entity.Runway;
import airport.repository.RunwayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunwayService {

    private final RunwayRepository runwayRepository;

    public RunwayService(RunwayRepository repository) {
        this.runwayRepository = repository;
    }

    public List<Runway> getAllRunways() {
        return runwayRepository.findAll();
    }
}
