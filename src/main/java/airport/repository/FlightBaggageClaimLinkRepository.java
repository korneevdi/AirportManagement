package airport.repository;

import airport.entity.FlightBaggageClaimLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightBaggageClaimLinkRepository extends JpaRepository<FlightBaggageClaimLink, Integer> {

    boolean existsByFlight_IdAndClaim_Id(Integer flightId, Integer claimId);

    void deleteByFlight_IdAndClaim_Id(Integer flightId, Integer claimId);

    @Query("""
    SELECT link
    FROM FlightBaggageClaimLink link
    WHERE link.flight.id = :flightId
    ORDER BY CAST(link.claim.number AS INTEGER) ASC
    """)
    List<FlightBaggageClaimLink> findByFlight_IdOrderByClaim_NumberAsc(@Param("flightId") Integer flightId);
}
