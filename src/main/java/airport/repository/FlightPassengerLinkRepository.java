package airport.repository;

import airport.entity.FlightPassengerLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightPassengerLinkRepository extends JpaRepository<FlightPassengerLink, Integer> {

    boolean existsByFlight_IdAndPassenger_Id(Integer flightId, Integer passengerId);

    void deleteByFlight_IdAndPassenger_Id(Integer flightId, Integer passengerId);
}
