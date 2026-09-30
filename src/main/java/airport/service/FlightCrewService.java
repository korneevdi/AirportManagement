package airport.service;

import airport.entity.FlightCrew;
import airport.repository.FlightCrewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightCrewService {

    private final FlightCrewRepository flightCrewRepository;

    public FlightCrewService(FlightCrewRepository repository) {
        this.flightCrewRepository = repository;
    }

    public List<FlightCrew> getAllFlightCrews() {
        return flightCrewRepository.findAllByOrderByLastNameAsc();
    }

    public FlightCrew getFlightCrewById(Integer id) {
        return flightCrewRepository.findById(id)
                .orElseThrow();
    }

    public void saveFlightCrew(FlightCrew crew) {
        flightCrewRepository.save(crew);
    }

    public void updateFlightCrew(FlightCrew crew, Integer id) {

        FlightCrew flightCrew = flightCrewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight crew not found"));

        flightCrew.setPilotLicenseNumber(crew.getPilotLicenseNumber());
        flightCrew.setFirstName(crew.getFirstName());
        flightCrew.setLastName(crew.getLastName());
        flightCrew.setSex(crew.getSex());
        flightCrew.setBirthDate(crew.getBirthDate());
        flightCrew.setPassCountry(crew.getPassCountry());
        flightCrew.setPassNumber(crew.getPassNumber());

        flightCrewRepository.save(flightCrew);
    }

    public void deleteFlightCrew(Integer id) {

        FlightCrew flightCrew = flightCrewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight crew not found"));

        flightCrewRepository.delete(flightCrew);
    }

    public boolean existsByPassportCountryAndPassportNumber(String country, String passportNumber) {
        return flightCrewRepository.existsByPassCountryAndPassNumber(country, passportNumber);
    }

    public boolean existsByPassportCountryAndPassportNumberAndIdNot(String country, String passportNumber, Integer id) {
        return flightCrewRepository.existsByPassCountryAndPassNumberAndIdNot(country, passportNumber, id);
    }
}
