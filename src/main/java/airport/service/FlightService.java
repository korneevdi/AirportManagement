package airport.service;

import airport.dto.FlightDetailsView;
import airport.dto.FlightForm;
import airport.dto.FlightView;
import airport.entity.*;
import airport.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    private final FlightBaggageClaimLinkRepository flightBaggageClaimLinkRepository;
    private final FlightCheckInLinkRepository flightCheckInLinkRepository;
    private final FlightCrewLinkRepository flightCrewLinkRepository;
    private final FlightPassengerLinkRepository flightPassengerLinkRepository;

    public FlightService(
            FlightRepository flightRepository,
            FlightBaggageClaimLinkRepository flightBaggageClaimLinkRepository,
            FlightCheckInLinkRepository flightCheckInLinkRepository,
            FlightCrewLinkRepository flightCrewLinkRepository,
            FlightPassengerLinkRepository flightPassengerLinkRepository) {
        this.flightRepository = flightRepository;
        this.flightBaggageClaimLinkRepository = flightBaggageClaimLinkRepository;
        this.flightCheckInLinkRepository = flightCheckInLinkRepository;
        this.flightCrewLinkRepository = flightCrewLinkRepository;
        this.flightPassengerLinkRepository = flightPassengerLinkRepository;
    }

    @Value("${airport.home.name}")
    private String homeAirportName;

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

    public List<FlightCheckInLink> getCheckInCountersForFlight(Integer flightId) {
        return flightCheckInLinkRepository
                .findByFlight_IdOrderByCounter_NumberAsc(flightId);
    }

    public List<FlightBaggageClaimLink> getBaggageClaimsForFlight(Integer flightId) {
        return flightBaggageClaimLinkRepository
                .findByFlight_IdOrderByClaim_NumberAsc(flightId);
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

    public boolean isDepartureFromHomeAirport(Flight flight) {
        return isHomeAirport(flight.getDepartureAirport());
    }

    public boolean isArrivalAtHomeAirport(Flight flight) {
        return isHomeAirport(flight.getArrivalAirport());
    }

    /* Manage baggage claims */

    public void addBaggageClaim(Integer flightId, BaggageClaim claim) {

        if(flightBaggageClaimLinkRepository.existsByFlight_IdAndClaim_Id(flightId, claim.getId())) {
            throw new IllegalArgumentException("Baggage claim is already assigned to this flight");
        }

        Flight flight = getFlightById(flightId);

        if(!isArrivalAtHomeAirport(flight)) {
            throw new IllegalArgumentException(
                    "Baggage claims cannot be assigned to a flight not arriving at the home airport"
            );
        }

        FlightBaggageClaimLink link = new FlightBaggageClaimLink();
        link.setFlight(flight);
        link.setClaim(claim);

        flightBaggageClaimLinkRepository.save(link);
    }

    @Transactional
    public void deleteBaggageClaim(Integer flightId, Integer claimId) {
        flightBaggageClaimLinkRepository.deleteByFlight_IdAndClaim_Id(flightId, claimId);
    }


    /* Manage check-in counters */

    public void addCheckInCounter(Integer flightId, CheckInCounter counter) {

        if(flightCheckInLinkRepository.existsByFlight_IdAndCounter_Id(flightId, counter.getId())) {
            throw new IllegalArgumentException("Check-in counter is already assigned to this flight");
        }

        Flight flight = getFlightById(flightId);

        if(!isDepartureFromHomeAirport(flight)) {
            throw new IllegalArgumentException(
                    "Check-in counters cannot be assigned to a flight not departing from the home airport"
            );
        }

        FlightCheckInLink link = new FlightCheckInLink();
        link.setFlight(flight);
        link.setCounter(counter);

        flightCheckInLinkRepository.save(link);
    }

    @Transactional
    public void deleteCheckInCounter(Integer flightId, Integer counterId) {
        flightCheckInLinkRepository.deleteByFlight_IdAndCounter_Id(flightId, counterId);
    }


    /* Manage flight crew */

    public void addFlightCrew(Integer flightId, FlightCrew crew, CrewRole role) {

        if(flightCrewLinkRepository.existsByFlight_IdAndCrew_IdAndRole_Id(flightId, crew.getId(), role.getId())) {
            throw new IllegalArgumentException("Flight crew is already assigned to this flight");
        }

        Flight flight = getFlightById(flightId);

        FlightCrewLink link = new FlightCrewLink();
        link.setFlight(flight);
        link.setCrew(crew);
        link.setRole(role);

        flightCrewLinkRepository.save(link);
    }

    @Transactional
    public void deleteFlightCrew(Integer flightId, Integer crewId, Integer roleId) {
        flightCrewLinkRepository.deleteByFlight_IdAndCrew_IdAndRole_Id(flightId, crewId, roleId);
    }

    public void updateFlightCrewRole(
            Integer flightId,
            Integer crewId,
            Integer oldRoleId,
            CrewRole newRole) {

        FlightCrewLink link = flightCrewLinkRepository
                .findByFlight_IdAndCrew_IdAndRole_Id(
                        flightId,
                        crewId,
                        oldRoleId
                )
                .orElseThrow(() -> new IllegalArgumentException(
                        "Crew member assignment not found"
                ));

        if (oldRoleId.equals(newRole.getId())) {
            return;
        }

        if (flightCrewLinkRepository
                .existsByFlight_IdAndCrew_IdAndRole_Id(
                        flightId,
                        crewId,
                        newRole.getId()
                )) {

            throw new IllegalArgumentException(
                    "This crew member already has this role on this flight"
            );
        }

        link.setRole(newRole);
        flightCrewLinkRepository.save(link);
    }


    /* Manage passengers */

    public void addPassenger(Integer flightId, Passenger passenger) {

        if(flightPassengerLinkRepository.existsByFlight_IdAndPassenger_Id(flightId, passenger.getId())) {
            throw new IllegalArgumentException("Passenger is already assigned to this flight");
        }

        Flight flight = getFlightById(flightId);

        FlightPassengerLink link = new FlightPassengerLink();
        link.setFlight(flight);
        link.setPassenger(passenger);
        link.setIsCheckedIn(false);
        link.setIsPassedControl(false);
        link.setIsBoarded(false);

        flightPassengerLinkRepository.save(link);
    }

    @Transactional
    public void deletePassenger(Integer flightId, Integer passengerId) {
        flightPassengerLinkRepository.deleteByFlight_IdAndPassenger_Id(flightId, passengerId);
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

    private boolean isHomeAirport(Airport airport) {
        return airport != null && homeAirportName.equals(airport.getName());
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
