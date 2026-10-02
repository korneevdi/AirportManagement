package airport.repository;

import airport.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findAllByOrderByServiceDateAsc();

    boolean existsByFlightNumberAndServiceDate(
            String flightNumber,
            LocalDate serviceDate
    );

    boolean existsByFlightNumberAndServiceDateAndIdNot(
            String flightNumber,
            LocalDate serviceDate,
            Integer id
    );
}
