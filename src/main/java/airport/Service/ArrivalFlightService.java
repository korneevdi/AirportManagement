package airport.Service;

import airport.model.ArrivalFlight;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArrivalFlightService {

    public List<ArrivalFlight> getArrivalFlights() {
        return List.of(
                new ArrivalFlight("RTA8", "Curacao", LocalDateTime.now(), "in air"),
                new ArrivalFlight("RTA8", "Curacao", LocalDateTime.now(), "in air"),
                new ArrivalFlight("RTA8", "Curacao", LocalDateTime.now(), "in air"),
                new ArrivalFlight("RTA8", "Curacao", LocalDateTime.now(), "in air"),
                new ArrivalFlight("RTA8", "Curacao", LocalDateTime.now(), "in air"),
                new ArrivalFlight("RTA8", "Curacao", LocalDateTime.now(), "landed")
        );
    }
}
