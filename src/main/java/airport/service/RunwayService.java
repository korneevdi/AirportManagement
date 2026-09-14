package airport.service;

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

    public Runway getRunwayById(Integer id) {
        return runwayRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Runway not found"));
    }

    public Runway saveRunway(Runway runway) {
        return runwayRepository.save(runway);
    }

    public void deleteRunway(Integer id) {
        runwayRepository.deleteById(id);
    }
}
