package airport.Service;

import airport.entity.Gate;
import airport.repository.GateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateService {

    private final GateRepository gateRepository;

    public GateService(GateRepository repository) {
        this.gateRepository = repository;
    }

    public List<Gate> getAllGates() {
        return gateRepository.findAll();
    }
}
