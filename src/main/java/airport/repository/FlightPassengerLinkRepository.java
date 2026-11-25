package airport.repository;

import airport.entity.FlightPassengerLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightPassengerLinkRepository extends JpaRepository<FlightPassengerLink, Integer> {

    boolean existsByFlight_IdAndPassenger_Id(Integer flightId, Integer passengerId);

    void deleteByFlight_IdAndPassenger_Id(Integer flightId, Integer passengerId);

    Optional<FlightPassengerLink> findByFlight_IdAndPassenger_Id(Integer flightId, Integer passengerId);
}
