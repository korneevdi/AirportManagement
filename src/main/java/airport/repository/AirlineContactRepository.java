package airport.repository;

import airport.entity.AirlineContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineContactRepository extends JpaRepository<AirlineContact, Integer> {
}
