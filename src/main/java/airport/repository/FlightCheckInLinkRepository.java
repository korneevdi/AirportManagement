package airport.repository;

import airport.entity.FlightCheckInLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightCheckInLinkRepository extends JpaRepository<FlightCheckInLink, Integer> {

    boolean existsByFlight_IdAndCounter_Id(Integer flightId, Integer counterId);

    void deleteByFlight_IdAndCounter_Id(Integer flightId, Integer counterId);

    @Query("""
            SELECT link
            FROM FlightCheckInLink link
            WHERE link.flight.id = :flightId
                ORDER BY
                    SUBSTRING(link.counter.number, 1, 1) ASC,
                    CAST(SUBSTRING(link.counter.number, 2) AS INTEGER) ASC
            """)
    List<FlightCheckInLink> findByFlight_IdOrderByCounter_NumberAsc(Integer flightId);
}
