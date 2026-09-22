package airport.repository;

import airport.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Integer> {

    List<Type> findAllByOrderByNameAsc();

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
