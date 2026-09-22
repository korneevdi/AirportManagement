package airport.repository;

import airport.entity.CheckInCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CheckInCounterRepository extends JpaRepository<CheckInCounter, Integer> {

    @Query(value = """
        SELECT *
        FROM check_in_counters
        ORDER BY
            LEFT(counter_number, 1),
            CAST(SUBSTRING(counter_number FROM 2) AS INTEGER)
        """,
            nativeQuery = true)
    List<CheckInCounter> findAllSorted();
}
