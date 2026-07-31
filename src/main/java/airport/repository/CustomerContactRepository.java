package airport.repository;

import airport.entity.CustomerContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerContactRepository extends JpaRepository<CustomerContact, Integer> {
}
