package airport.repository;

import airport.entity.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GateRepository extends JpaRepository<Gate, Integer> {

    @Query(value = """
        SELECT *
        FROM gates
        ORDER BY
            LEFT(gate_number, 1),
            CAST(SUBSTRING(gate_number FROM 2) AS INTEGER)
        """,
            nativeQuery = true)
    List<Gate> findAllSorted();
}
