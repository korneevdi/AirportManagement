package airport.service;

import airport.dto.FlightDetailsView;
import airport.dto.FlightForm;
import airport.dto.FlightView;
import airport.entity.Airport;
import airport.entity.Flight;
import airport.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        return flightRepository.findAllByOrderByServiceDateAsc();
    }

    public Flight getFlightById(Integer id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));
    }

    public List<FlightView> getAllFlightViews() {

        return flightRepository.findAllByOrderByServiceDateAsc()
                .stream()
                .map(this::toFlightView)
                .toList();
    }

    public FlightDetailsView getFlightDetailsView(Integer id) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Flight not found"));

        FlightDetailsView view = new FlightDetailsView();

        view.setFlight(flight);
        view.setServiceDate(flight.getServiceDate());

        view.setScheduledDepartureTime(
                toLocalDateTime(
                        flight.getScheduledDepartureTime(),
                        flight.getDepartureAirport()
                )
        );

        view.setActualDepartureTime(
                toLocalDateTime(
                        flight.getActualDepartureTime(),
                        flight.getDepartureAirport()
                )
        );

        view.setScheduledArrivalTime(
                toLocalDateTime(
                        flight.getScheduledArrivalTime(),
                        flight.getArrivalAirport()
                )
        );

        view.setActualArrivalTime(
                toLocalDateTime(
                        flight.getActualArrivalTime(),
                        flight.getArrivalAirport()
                )
        );

        return view;
    }

    public FlightForm getFlightFormById(Integer id) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        FlightForm form = new FlightForm();

        form.setId(flight.getId());
        form.setFlightNumber(flight.getFlightNumber());
        form.setServiceDate(flight.getServiceDate());
        form.setAirline(flight.getAirline());
        form.setDepartureAirport(flight.getDepartureAirport());
        form.setArrivalAirport(flight.getArrivalAirport());

        /*
         * Convert the stored instant back to the local time
         * of the corresponding airport.
         */
        form.setScheduledDepartureTime(
                toLocalDateTime(
                        flight.getScheduledDepartureTime(),
                        flight.getDepartureAirport()
                )
        );

        form.setActualDepartureTime(
                toLocalDateTime(
                        flight.getActualDepartureTime(),
                        flight.getDepartureAirport()
                )
        );

        form.setScheduledArrivalTime(
                toLocalDateTime(
                        flight.getScheduledArrivalTime(),
                        flight.getArrivalAirport()
                )
        );

        form.setActualArrivalTime(
                toLocalDateTime(
                        flight.getActualArrivalTime(),
                        flight.getArrivalAirport()
                )
        );

        form.setAirplane(flight.getAirplane());
        form.setResponsibleDispatcher(flight.getResponsibleDispatcher());
        form.setFlightType(flight.getFlightType());
        form.setCustomer(flight.getCustomer());
        form.setStatus(flight.getStatus());
        form.setPassControlType(flight.getPassControlType());
        form.setDepartureGate(flight.getDepartureGate());
        form.setArrivalGate(flight.getArrivalGate());
        form.setDepartureTerminal(flight.getDepartureTerminal());
        form.setArrivalTerminal(flight.getArrivalTerminal());
        form.setRunway(flight.getRunway());

        return form;
    }

    public void saveFlight(FlightForm form) {

        Flight flight = new Flight();

        flight.setFlightNumber(form.getFlightNumber());
        flight.setServiceDate(form.getServiceDate());
        flight.setAirline(form.getAirline());
        flight.setDepartureAirport(form.getDepartureAirport());
        flight.setArrivalAirport(form.getArrivalAirport());

        // Departure time is entered by the user
        // in the local time of the departure airport.
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

        // Arrival time is entered by the user
        // in the local time of the arrival airport.
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

    public void updateFlight(FlightForm form, Integer id) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

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

    public void deleteFlight(Integer id) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        flightRepository.delete(flight);
    }

    public boolean existsByFlightNumberAndServiceDate(
            String flightNumber,
            LocalDate serviceDate) {

        return flightRepository.existsByFlightNumberAndServiceDate(
                flightNumber,
                serviceDate
        );
    }

    public boolean existsByFlightNumberAndServiceDateAndIdNot(
            String flightNumber,
            LocalDate serviceDate,
            Integer id) {

        return flightRepository.existsByFlightNumberAndServiceDateAndIdNot(
                flightNumber,
                serviceDate,
                id
        );
    }

    /**
     * Converts user-entered local airport time into an OffsetDateTime.
     *
     * Example:
     *
     * 2026-10-05 10:00
     * + Asia/Dubai
     *
     * becomes:
     *
     * 2026-10-05T10:00+04:00
     */
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

    /**
     * Converts a stored OffsetDateTime into local time
     * of the specified airport.
     *
     * Important: atZoneSameInstant() is used here because
     * we want to preserve the actual moment in time and only
     * change how that moment is represented in the airport's
     * local timezone.
     */
    private LocalDateTime toLocalDateTime(
            OffsetDateTime dateTime,
            Airport airport) {

        if (dateTime == null) {
            return null;
        }

        ZoneId zoneId = ZoneId.of(airport.getTimeZone());

        return dateTime
                .atZoneSameInstant(zoneId)
                .toLocalDateTime();
    }

    private FlightView toFlightView(Flight flight) {

        FlightView view = new FlightView();

        view.setId(flight.getId());
        view.setFlightNumber(flight.getFlightNumber());
        view.setServiceDate(flight.getServiceDate());

        view.setAirlineName(flight.getAirline().getName());

        view.setDepartureAirport(
                flight.getDepartureAirport().getIata()
                        + ", "
                        + flight.getDepartureAirport().getCity()
                        + ", "
                        + flight.getDepartureAirport().getCountry()
        );

        view.setArrivalAirport(
                flight.getArrivalAirport().getIata()
                        + ", "
                        + flight.getArrivalAirport().getCity()
                        + ", "
                        + flight.getArrivalAirport().getCountry()
        );

        view.setScheduledDepartureTime(
                toLocalDateTime(
                        flight.getScheduledDepartureTime(),
                        flight.getDepartureAirport()
                )
        );

        view.setActualDepartureTime(
                toLocalDateTime(
                        flight.getActualDepartureTime(),
                        flight.getDepartureAirport()
                )
        );

        view.setScheduledArrivalTime(
                toLocalDateTime(
                        flight.getScheduledArrivalTime(),
                        flight.getArrivalAirport()
                )
        );

        view.setActualArrivalTime(
                toLocalDateTime(
                        flight.getActualArrivalTime(),
                        flight.getArrivalAirport()
                )
        );

        view.setAirplaneModel(flight.getAirplane().getModel());
        view.setStatusName(flight.getStatus().getName());

        return view;
    }
}
