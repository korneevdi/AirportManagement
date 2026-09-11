package airport.service;

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

    public Gate getGateById(Integer id) {
        return gateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gate not found"));
    }

    public Gate saveGate(Gate gate) {
        return gateRepository.save(gate);
    }

    public void deleteGate(Integer id) {
        gateRepository.deleteById(id);
    }
}
