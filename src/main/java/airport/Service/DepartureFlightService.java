package airport.Service;

import airport.model.DepartureFlight;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepartureFlightService {

    public List<DepartureFlight> getDepartureFlights() {
        return List.of(
                new DepartureFlight("RTA8", "Havana", LocalDateTime.now(), "boarding"),
                new DepartureFlight("RTA8", "Havana", LocalDateTime.now(), "boarding"),
                new DepartureFlight("RTA8", "Havana", LocalDateTime.now(), "boarding"),
                new DepartureFlight("RTA8", "Havana", LocalDateTime.now(), "boarding"),
                new DepartureFlight("RTA8", "Havana", LocalDateTime.now(), "boarding"),
                new DepartureFlight("RTA8", "Havana", LocalDateTime.now(), "boarding")
        );
    }
}
