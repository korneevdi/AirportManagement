package airport.repository;

import airport.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirplaneRepository extends JpaRepository<Airplane, Integer> {

    List<Airplane> findAllByOrderByModelAsc();
}
