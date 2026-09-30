package airport.service;

import airport.entity.Airplane;
import airport.entity.Passenger;
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
        return airplaneRepository.findAllByOrderByModelAsc();
    }

    public Airplane getAirplaneById(Integer id) {
        return airplaneRepository.findById(id)
                .orElseThrow();
    }

    public void saveAirplane(Airplane airplane) {
        airplaneRepository.save(airplane);
    }

    public void updateAirplane(Airplane airplane, Integer id) {

        Airplane newAirplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airplane not found"));

        newAirplane.setAirline(airplane.getAirline());
        newAirplane.setRegistrationNumber(airplane.getRegistrationNumber());
        newAirplane.setModel(airplane.getModel());
        newAirplane.setCapacity(airplane.getCapacity());
        newAirplane.setType(airplane.getType());

        airplaneRepository.save(newAirplane);
    }

    public void deleteAirplane(Integer id) {

        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airplane not found"));

        airplaneRepository.delete(airplane);
    }

    public boolean existsByRegistrationNumber(String registrationNumber) {
        return airplaneRepository.existsByRegistrationNumber(registrationNumber);
    }

    public boolean existsByRegistrationNumberAndIdNot(String registrationNumber, Integer id) {
        return airplaneRepository.existsByRegistrationNumberAndIdNot(registrationNumber, id);
    }
}
