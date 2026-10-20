package airport.repository;

import airport.entity.FlightCrewLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightCrewLinkRepository extends JpaRepository<FlightCrewLink, Integer> {

    boolean existsByFlight_IdAndCrew_IdAndRole_Id(Integer flightId, Integer claimId, Integer roleId);

    Optional<FlightCrewLink> findByFlight_IdAndCrew_IdAndRole_Id(Integer flightId, Integer crewId, Integer roleId);

    void deleteByFlight_IdAndCrew_IdAndRole_Id(Integer flightId, Integer claimId, Integer roleId);
}
