package airport.service;

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
        return passengerRepository.findAllByOrderByLastNameAsc();
    }

    public Passenger getPassengerById(Integer id) {
        return passengerRepository.findById(id)
                .orElseThrow();
    }

    public void savePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    public void updatePassenger(Passenger passenger, Integer id) {

        Passenger newPassenger = passengerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Passenger not found"));

        newPassenger.setFirstName(passenger.getFirstName());
        newPassenger.setLastName(passenger.getLastName());
        newPassenger.setSex(passenger.getSex());
        newPassenger.setAge(passenger.getAge());
        newPassenger.setPassCountry(passenger.getPassCountry());
        newPassenger.setPassNumber(passenger.getPassNumber());

        passengerRepository.save(newPassenger);
    }

    public void deletePassenger(Integer id) {

        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Passenger not found"));

        passengerRepository.delete(passenger);
    }

    public boolean existsByPassportCountryAndPassportNumber(String country, String passportNumber) {
        return passengerRepository.existsByPassCountryAndPassNumber(country, passportNumber);
    }

    public boolean existsByPassportCountryAndPassportNumberAndIdNot(String country, String passportNumber, Integer id) {
        return passengerRepository.existsByPassCountryAndPassNumberAndIdNot(country, passportNumber, id);
    }
}
