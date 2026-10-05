package airport.dto;

import airport.entity.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class FlightForm {

    private Integer id;

    @NotBlank(message = "Flight number is required")
    @Size(max = 10, message = "Flight number must not contain more than 10 symbols")
    private String flightNumber;

    @NotNull(message = "Service date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate serviceDate;

    @NotNull(message = "Airline is required")
    private Airline airline;

    @NotNull(message = "Departure airport is required")
    private Airport departureAirport;

    @NotNull(message = "Arrival airport is required")
    private Airport arrivalAirport;

    @NotNull(message = "Scheduled departure time is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime scheduledDepartureTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime actualDepartureTime;

    @NotNull(message = "Scheduled arrival time is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime scheduledArrivalTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime actualArrivalTime;

    @NotNull(message = "Airplane is required")
    private Airplane airplane;

    @NotNull(message = "Responsible dispatcher is required")
    private AirportEmployee responsibleDispatcher;

    @NotNull(message = "Flight type is required")
    private Type flightType;

    private Customer customer;

    @NotNull(message = "Flight status is required")
    private FlightStatus status;

    @NotNull(message = "Passport control type is required")
    private PassControlType passControlType;

    private Gate departureGate;

    private Gate arrivalGate;

    private Terminal departureTerminal;

    private Terminal arrivalTerminal;

    private Runway runway;
}
