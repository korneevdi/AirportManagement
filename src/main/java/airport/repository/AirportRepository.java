package airport.repository;

import airport.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

    List<Airport> findAllByOrderByNameAsc();

    boolean existsByIata(String iata);

    boolean existsByIataAndIdNot(String iata, Integer id);

    boolean existsByIcao(String icao);

    boolean existsByIcaoAndIdNot(String icao, Integer id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
