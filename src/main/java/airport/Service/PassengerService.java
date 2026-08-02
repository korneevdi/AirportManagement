package airport.Service;

import airport.entity.Passenger;
import airport.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository repository) {
        this.passengerRepository = repository;
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassenger(Integer id) {
        return passengerRepository.findById(id)
                .orElseThrow();
    }
}
