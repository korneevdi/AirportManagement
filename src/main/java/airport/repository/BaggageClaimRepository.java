package airport.repository;

import airport.entity.BaggageClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BaggageClaimRepository extends JpaRepository<BaggageClaim, Integer> {

    @Query(value = """
    SELECT *
    FROM baggage_claims
    ORDER BY CAST(claim_number AS INTEGER)
    """,
            nativeQuery = true)
    List<BaggageClaim> findAllSorted();

    boolean existsByNumber(String number);

    boolean existsByNumberAndIdNot(String number, Integer id);
}
