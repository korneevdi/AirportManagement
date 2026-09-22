package airport.repository;

import airport.entity.Sex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SexRepository extends JpaRepository<Sex, Integer> {

    List<Sex> findAllByOrderByNameAsc();

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
