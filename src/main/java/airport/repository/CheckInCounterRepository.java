package airport.repository;

import airport.entity.CheckInCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInCounterRepository extends JpaRepository<CheckInCounter, Integer> {
}
