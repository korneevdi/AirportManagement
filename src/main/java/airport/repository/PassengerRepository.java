package airport.repository;

import airport.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    List<Passenger> findAllByOrderByLastNameAsc();

    boolean existsByPassCountryAndPassNumber(
            String passportCountry,
            String passportNumber
    );

    boolean existsByPassCountryAndPassNumberAndIdNot(
            String passportCountry,
            String passportNumber,
            Integer id
    );
}
