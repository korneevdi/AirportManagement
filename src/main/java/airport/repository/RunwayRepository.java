package airport.repository;

import airport.entity.Runway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunwayRepository extends JpaRepository<Runway, Integer> {
}
