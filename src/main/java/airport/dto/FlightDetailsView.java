package airport.dto;

import airport.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class FlightDetailsView {

    private Flight flight;

    private LocalDate serviceDate;

    private LocalDateTime scheduledDepartureTime;
    private LocalDateTime actualDepartureTime;

    private LocalDateTime scheduledArrivalTime;
    private LocalDateTime actualArrivalTime;
}
