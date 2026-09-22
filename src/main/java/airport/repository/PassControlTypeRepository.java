package airport.repository;

import airport.entity.PassControlType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassControlTypeRepository extends JpaRepository<PassControlType, Integer> {

    List<PassControlType> findAllByOrderByNameAsc();

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
