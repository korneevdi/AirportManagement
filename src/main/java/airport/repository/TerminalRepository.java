package airport.repository;

import airport.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminalRepository extends JpaRepository<Terminal, Integer> {

    List<Terminal> findAllByOrderByNumberAsc();

    boolean existsByNumber(String number);

    boolean existsByNumberAndIdNot(String number, Integer id);
}
