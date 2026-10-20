package airport.controller;

import airport.dto.FlightDetailsView;
import airport.dto.FlightForm;
import airport.entity.*;
import airport.service.*;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FlightController {

    private final FlightService flightService;
    private final AirlineService airlineService;
    private final AirportService airportService;
    private final AirplaneService airplaneService;
    private final AirportEmployeeService airportEmployeeService;
    private final TypeService typeService;
    private final CustomerService customerService;
    private final FlightStatusService flightStatusService;
    private final PassControlTypeService passControlTypeService;
    private final GateService gateService;
    private final TerminalService terminalService;
    private final RunwayService runwayService;
    private final BaggageClaimService baggageClaimService;
    private final CheckInCounterService checkInCounterService;
    private final CrewRoleService crewRoleService;
    private final FlightCrewService flightCrewService;
    private final PassengerService passengerService;

    public FlightController(
            FlightService flightService,
            AirlineService airlineService,
            AirportService airportService,
            AirplaneService airplaneService,
            AirportEmployeeService airportEmployeeService,
            TypeService typeService,
            CustomerService customerService,
            FlightStatusService flightStatusService,
            PassControlTypeService passControlTypeService,
            GateService gateService,
            TerminalService terminalService,
            RunwayService runwayService,
            BaggageClaimService baggageClaimService,
            CheckInCounterService checkInCounterService,
            CrewRoleService crewRoleService,
            FlightCrewService flightCrewService,
            PassengerService passengerService) {
        this.flightService = flightService;
        this.airlineService = airlineService;
        this.airportService = airportService;
        this.airplaneService = airplaneService;
        this.airportEmployeeService = airportEmployeeService;
        this.typeService = typeService;
        this.customerService = customerService;
        this.flightStatusService = flightStatusService;
        this.passControlTypeService = passControlTypeService;
        this.gateService = gateService;
        this.terminalService = terminalService;
        this.runwayService = runwayService;
        this.baggageClaimService = baggageClaimService;
        this.checkInCounterService = checkInCounterService;
        this.crewRoleService = crewRoleService;
        this.flightCrewService = flightCrewService;
        this.passengerService = passengerService;
    }


    /* Get flight info */

    @GetMapping("/flights")
    public String getFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlightViews());
        return "flights";
    }

    @GetMapping("/flights/{id}")
    public String getFlight(@PathVariable Integer id, Model model) {

        FlightDetailsView flight = flightService.getFlightDetailsView(id);

        model.addAttribute("flight", flight);

        model.addAttribute(
                "departureFromHomeAirport",
                flightService.isDepartureFromHomeAirport(flight.getFlight())
        );

        model.addAttribute(
                "arrivalAtHomeAirport",
                flightService.isArrivalAtHomeAirport(flight.getFlight())
        );

        model.addAttribute("passengers", passengerService.getAllPassengers());
        model.addAttribute("flightCrews", flightCrewService.getAllFlightCrews());
        model.addAttribute("crewRoles", crewRoleService.getAllCrewRoles());
        model.addAttribute("checkInCounters", checkInCounterService.getAllCheckInCounters());
        model.addAttribute("baggageClaims", baggageClaimService.getAllBaggageClimes());

        // Sorted lists for showing baggage claims and check-in counters
        model.addAttribute("flightBaggageClaims", flightService.getBaggageClaimsForFlight(id));
        model.addAttribute("flightCheckInCounters", flightService.getCheckInCountersForFlight(id));

        return "flight";
    }


    /* Manage flight */

    @GetMapping("/flights/new")
    public String showCreateFlightForm(Model model) {
        model.addAttribute("flight", new FlightForm());

        model.addAttribute("airlines", airlineService.getAllAirlines());
        model.addAttribute("airports", airportService.getAllAirports());
        model.addAttribute("airplanes", airplaneService.getAllAirplanes());
        model.addAttribute("airportEmployees", airportEmployeeService.getAllAirportEmployees());
        model.addAttribute("dispatchers", airportEmployeeService.getAllDispatchers());
        model.addAttribute("flightTypes", typeService.getAllTypes());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("flightStatuses", flightStatusService.getAllFlightStatuses());
        model.addAttribute("passControlTypes", passControlTypeService.getAllPassControlTypes());
        model.addAttribute("gates", gateService.getAllGates());
        model.addAttribute("terminals", terminalService.getAllTerminals());
        model.addAttribute("runways", runwayService.getAllRunways());

        return "flight-form";
    }

    @PostMapping("/flights")
    public String createFlight(
            @Valid @ModelAttribute("flight") FlightForm form,
            BindingResult bindingResult,
            Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("airlines", airlineService.getAllAirlines());
            model.addAttribute("airports", airportService.getAllAirports());
            model.addAttribute("airplanes", airplaneService.getAllAirplanes());
            model.addAttribute("airportEmployees", airportEmployeeService.getAllAirportEmployees());
            model.addAttribute("dispatchers", airportEmployeeService.getAllDispatchers());
            model.addAttribute("flightTypes", typeService.getAllTypes());
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("flightStatuses", flightStatusService.getAllFlightStatuses());
            model.addAttribute("passControlTypes", passControlTypeService.getAllPassControlTypes());
            model.addAttribute("gates", gateService.getAllGates());
            model.addAttribute("terminals", terminalService.getAllTerminals());
            model.addAttribute("runways", runwayService.getAllRunways());
            return "flight-form";
        }

        if (flightService.existsByFlightNumberAndServiceDate(
                form.getFlightNumber(),
                form.getServiceDate())) {
            bindingResult.rejectValue(
                    "flight number",
                    "duplicate",
                    "Flight with this number and service date already exists"
            );
        }

        if(bindingResult.hasErrors()) {
            model.addAttribute("airlines", airlineService.getAllAirlines());
            model.addAttribute("airports", airportService.getAllAirports());
            model.addAttribute("airplanes", airplaneService.getAllAirplanes());
            model.addAttribute("airportEmployees", airportEmployeeService.getAllAirportEmployees());
            model.addAttribute("dispatchers", airportEmployeeService.getAllDispatchers());
            model.addAttribute("flightTypes", typeService.getAllTypes());
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("flightStatuses", flightStatusService.getAllFlightStatuses());
            model.addAttribute("passControlTypes", passControlTypeService.getAllPassControlTypes());
            model.addAttribute("gates", gateService.getAllGates());
            model.addAttribute("terminals", terminalService.getAllTerminals());
            model.addAttribute("runways", runwayService.getAllRunways());
            return "flight-form";
        }

        flightService.saveFlight(form);
        return "redirect:/flights";
    }

    @GetMapping("/flights/{id}/edit")
    public String showEditFlightForm(@PathVariable Integer id, Model model) {
        model.addAttribute("flight", flightService.getFlightFormById(id));
        model.addAttribute("airlines", airlineService.getAllAirlines());
        model.addAttribute("airports", airportService.getAllAirports());
        model.addAttribute("airplanes", airplaneService.getAllAirplanes());
        model.addAttribute("airportEmployees", airportEmployeeService.getAllAirportEmployees());
        model.addAttribute("dispatchers", airportEmployeeService.getAllDispatchers());
        model.addAttribute("flightTypes", typeService.getAllTypes());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("flightStatuses", flightStatusService.getAllFlightStatuses());
        model.addAttribute("passControlTypes", passControlTypeService.getAllPassControlTypes());
        model.addAttribute("gates", gateService.getAllGates());
        model.addAttribute("terminals", terminalService.getAllTerminals());
        model.addAttribute("runways", runwayService.getAllRunways());
        return "flight-form";
    }

    @PostMapping("/flights/{id}")
    public String updateFlight(
            @PathVariable Integer id,
            @Valid @ModelAttribute("flight") FlightForm form,
            BindingResult bindingResult,
            Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("airlines", airlineService.getAllAirlines());
            model.addAttribute("airports", airportService.getAllAirports());
            model.addAttribute("airplanes", airplaneService.getAllAirplanes());
            model.addAttribute("airportEmployees", airportEmployeeService.getAllAirportEmployees());
            model.addAttribute("dispatchers", airportEmployeeService.getAllDispatchers());
            model.addAttribute("flightTypes", typeService.getAllTypes());
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("flightStatuses", flightStatusService.getAllFlightStatuses());
            model.addAttribute("passControlTypes", passControlTypeService.getAllPassControlTypes());
            model.addAttribute("gates", gateService.getAllGates());
            model.addAttribute("terminals", terminalService.getAllTerminals());
            model.addAttribute("runways", runwayService.getAllRunways());
            return "flight-form";
        }

        if (flightService.existsByFlightNumberAndServiceDateAndIdNot(
                form.getFlightNumber(),
                form.getServiceDate(), id)) {
            bindingResult.rejectValue(
                    "flight number",
                    "duplicate",
                    "Flight with this number and service date already exists"
            );
        }

        if(bindingResult.hasErrors()) {
            model.addAttribute("airlines", airlineService.getAllAirlines());
            model.addAttribute("airports", airportService.getAllAirports());
            model.addAttribute("airplanes", airplaneService.getAllAirplanes());
            model.addAttribute("airportEmployees", airportEmployeeService.getAllAirportEmployees());
            model.addAttribute("dispatchers", airportEmployeeService.getAllDispatchers());
            model.addAttribute("flightTypes", typeService.getAllTypes());
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("flightStatuses", flightStatusService.getAllFlightStatuses());
            model.addAttribute("passControlTypes", passControlTypeService.getAllPassControlTypes());
            model.addAttribute("gates", gateService.getAllGates());
            model.addAttribute("terminals", terminalService.getAllTerminals());
            model.addAttribute("runways", runwayService.getAllRunways());
            return "flight-form";
        }

        flightService.updateFlight(form, id);
        return "redirect:/flights/" + id;
    }

    @PostMapping("/flights/{id}/delete")
    public String deleteFlight(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            flightService.deleteFlight(id);
            return "redirect:/flights";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "flightDeleteError",
                    "Cannot delete flight because it is used by one or more objects."
            );
            redirectAttributes.addFlashAttribute("errorFlightId", id);
        }
        return "redirect:/flights/" + id;
    }


    /* Manage baggage claims */

    @PostMapping("/flights/{flightId}/baggage-claims/new")
    public String addBaggageClaim(
            @PathVariable Integer flightId,
            @RequestParam Integer claimId,
            RedirectAttributes redirectAttributes) {

        try{
            BaggageClaim claim = baggageClaimService.getBaggageClaimById(claimId);
            flightService.addBaggageClaim(flightId, claim);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute(
                    "baggageClaimAddError",
                    e.getMessage()
            );
        }

        return "redirect:/flights/" + flightId;
    }

    @PostMapping("/flights/{flightId}/baggage-claims/{claimId}/delete")
    public String deleteBaggageClaim(
            @PathVariable Integer flightId,
            @PathVariable Integer claimId) {

        flightService.deleteBaggageClaim(flightId, claimId);

        return "redirect:/flights/" + flightId;
    }


    /* Manage check-in counters */

    @PostMapping("/flights/{flightId}/check-in-counters/new")
    public String addCheckInCounter(
            @PathVariable Integer flightId,
            @RequestParam Integer counterId,
            RedirectAttributes redirectAttributes) {

        try{
            CheckInCounter counter = checkInCounterService.getCheckInCounterById(counterId);
            flightService.addCheckInCounter(flightId, counter);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute(
                    "checkInCounterAddError",
                    e.getMessage()
            );
        }

        return "redirect:/flights/" + flightId;
    }

    @PostMapping("/flights/{flightId}/check-in-counters/{counterId}/delete")
    public String deleteCheckInCounter(
            @PathVariable Integer flightId,
            @PathVariable Integer counterId) {

        flightService.deleteCheckInCounter(flightId, counterId);

        return "redirect:/flights/" + flightId;
    }


    /* Manage flight crew */

    @PostMapping("/flights/{flightId}/flight-crews/new")
    public String addFlightCrew(
            @PathVariable Integer flightId,
            @RequestParam Integer crewId,
            @RequestParam Integer roleId,
            RedirectAttributes redirectAttributes) {

        try{
            FlightCrew crew = flightCrewService.getFlightCrewById(crewId);
            CrewRole role = crewRoleService.getCrewRoleById(roleId);
            flightService.addFlightCrew(flightId, crew, role);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute(
                    "crewAddError",
                    e.getMessage()
            );
        }

        return "redirect:/flights/" + flightId;
    }

    @PostMapping("/flights/{flightId}/flight-crews/{crewId}/{roleId}/delete")
    public String deleteFlightCrew(
            @PathVariable Integer flightId,
            @PathVariable Integer crewId,
            @PathVariable Integer roleId) {

        flightService.deleteFlightCrew(flightId, crewId, roleId);

        return "redirect:/flights/" + flightId;
    }

    @PostMapping("/flights/{flightId}/flight-crews/{crewId}/role")
    public String updateFlightCrewRole(
            @PathVariable Integer flightId,
            @PathVariable Integer crewId,
            @RequestParam Integer oldRoleId,
            @RequestParam Integer roleId,
            RedirectAttributes redirectAttributes) {

        try{
            CrewRole newRole = crewRoleService.getCrewRoleById(roleId);
            flightService.updateFlightCrewRole(flightId, crewId, oldRoleId, newRole);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("crewRoleError", e.getMessage());
            redirectAttributes.addFlashAttribute("errorCrewId", crewId);
        }

        return "redirect:/flights/" + flightId;
    }


    /* Manage passengers */

    @PostMapping("/flights/{flightId}/passengers/new")
    public String addPassenger(
            @PathVariable Integer flightId,
            @RequestParam Integer passengerId,
            RedirectAttributes redirectAttributes) {

        try{
            Passenger passenger = passengerService.getPassengerById(passengerId);
            flightService.addPassenger(flightId, passenger);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute(
                    "passengerAddError",
                    e.getMessage()
            );
        }

        return "redirect:/flights/" + flightId;
    }

    @PostMapping("/flights/{flightId}/passengers/{passengerId}/delete")
    public String deletePassenger(
            @PathVariable Integer flightId,
            @PathVariable Integer passengerId) {

        flightService.deletePassenger(flightId, passengerId);

        return "redirect:/flights/" + flightId;
    }
}
