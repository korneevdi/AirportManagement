package airport.service;

import airport.entity.Airport;
import airport.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository repository) {
        this.airportRepository = repository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAllByOrderByNameAsc();
    }

    public Airport getAirportById(Integer id) {
        return  airportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airport not found"));
    }

    public boolean existsByIata(String iata) {
        return airportRepository.existsByIata(iata);
    }

    public boolean existsByIataAndIdNot(String iata, Integer id) {
        return airportRepository.existsByIataAndIdNot(iata, id);
    }

    public boolean existsByIcao(String icao) {
        return airportRepository.existsByIcao(icao);
    }

    public boolean existsByIcaoAndIdNot(String icao, Integer id) {
        return airportRepository.existsByIcaoAndIdNot(icao, id);
    }

    public boolean existsByName(String name) {
        return airportRepository.existsByName(name);
    }

    public boolean existsByNameAndIdNot(String name, Integer id) {
        return airportRepository.existsByNameAndIdNot(name, id);
    }

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public void deleteAirport(Integer id) {
        airportRepository.deleteById(id);
    }
}
