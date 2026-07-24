package airport.Service;

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
}
