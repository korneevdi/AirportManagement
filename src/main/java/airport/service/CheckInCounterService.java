package airport.service;

import airport.entity.CheckInCounter;
import airport.repository.CheckInCounterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInCounterService {

    private final CheckInCounterRepository checkInCounterRepository;

    public CheckInCounterService(CheckInCounterRepository repository) {
        this.checkInCounterRepository = repository;
    }

    public List<CheckInCounter> getAllCheckInCounters() {
        return checkInCounterRepository.findAll();
    }

    public CheckInCounter getCheckInCounterById(Integer id) {
        return checkInCounterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chack-in counter not found"));
    }

    public CheckInCounter saveCheckInCounter(CheckInCounter counter) {
        return checkInCounterRepository.save(counter);
    }

    public void deleteCheckInCounter(Integer id) {
        checkInCounterRepository.deleteById(id);
    }
}
