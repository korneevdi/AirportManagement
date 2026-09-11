package airport.service;

import airport.dto.FlightForm;
import airport.entity.Airport;
import airport.entity.Flight;
import airport.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository repository) {
        this.flightRepository = repository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Integer id) {
        return flightRepository.findById(id)
                .orElseThrow();
    }

    public void saveFlight(FlightForm form) {

        Flight flight = new Flight();
        flight.setFlightNumber(form.getFlightNumber());
        flight.setServiceDate(form.getServiceDate());
        flight.setAirline(form.getAirline());
        flight.setDepartureAirport(form.getDepartureAirport());
        flight.setArrivalAirport(form.getArrivalAirport());
        flight.setScheduledDepartureTime(
                toOffsetDateTime(
                        form.getScheduledDepartureTime(),
                        form.getDepartureAirport()
                )
        );
        flight.setActualDepartureTime(
                toOffsetDateTime(
                        form.getActualDepartureTime(),
                        form.getDepartureAirport()
                )
        );
        flight.setScheduledArrivalTime(
                toOffsetDateTime(
                        form.getScheduledArrivalTime(),
                        form.getArrivalAirport()
                )
        );
        flight.setActualArrivalTime(
                toOffsetDateTime(
                        form.getActualArrivalTime(),
                        form.getArrivalAirport()
                )
        );
        flight.setAirplane(form.getAirplane());
        flight.setResponsibleDispatcher(form.getResponsibleDispatcher());
        flight.setFlightType(form.getFlightType());
        flight.setCustomer(form.getCustomer());
        flight.setStatus(form.getStatus());
        flight.setPassControlType(form.getPassControlType());
        flight.setDepartureGate(form.getDepartureGate());
        flight.setArrivalGate(form.getArrivalGate());
        flight.setDepartureTerminal(form.getDepartureTerminal());
        flight.setArrivalTerminal(form.getArrivalTerminal());
        flight.setRunway(form.getRunway());
        flightRepository.save(flight);
    }

    private OffsetDateTime toOffsetDateTime(
            LocalDateTime dateTime,
            Airport airport) {

        if (dateTime == null) {
            return null;
        }

        ZoneId zoneId = ZoneId.of(airport.getTimeZone());

        return dateTime
                .atZone(zoneId)
                .toOffsetDateTime();
    }
}
