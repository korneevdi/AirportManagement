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
        return checkInCounterRepository.findAllSorted();
    }

    public CheckInCounter getCheckInCounterById(Integer id) {
        return checkInCounterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Check-in counter not found"));
    }

    public boolean existsByNumber(String number) {
        return checkInCounterRepository.existsByNumber(number);
    }

    public boolean existsByNumberAndIdNot(String number, Integer id) {
        return checkInCounterRepository.existsByNumberAndIdNot(number, id);
    }

    public CheckInCounter saveCheckInCounter(CheckInCounter counter) {
        return checkInCounterRepository.save(counter);
    }

    public void deleteCheckInCounter(Integer id) {
        checkInCounterRepository.deleteById(id);
    }
}
