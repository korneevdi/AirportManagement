package airport.repository;

import airport.entity.CrewRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewRoleRepository extends JpaRepository<CrewRole, Integer> {

    List<CrewRole> findAllByOrderByNameAsc();

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
