package airport.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class FlightView {

    private Integer id;

    private String flightNumber;

    private LocalDate serviceDate;

    private String airlineName;

    private String departureAirport;
    private String arrivalAirport;

    private LocalDateTime scheduledDepartureTime;
    private LocalDateTime actualDepartureTime;

    private LocalDateTime scheduledArrivalTime;
    private LocalDateTime actualArrivalTime;

    private String airplaneModel;

    private String statusName;
}
