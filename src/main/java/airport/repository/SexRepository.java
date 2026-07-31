package airport.repository;

import airport.entity.Sex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SexRepository extends JpaRepository<Sex, Integer> {
}
