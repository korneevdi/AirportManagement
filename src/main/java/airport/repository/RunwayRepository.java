package airport.repository;

import airport.entity.Runway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RunwayRepository extends JpaRepository<Runway, Integer> {

    @Query(value = """
        SELECT *
        FROM flight_runways
        ORDER BY
            CAST(SUBSTRING(runway_number FROM 1 FOR 2) AS INTEGER),
            RIGHT(runway_number, 1)
        """,
            nativeQuery = true)
    List<Runway> findAllSorted();
}
