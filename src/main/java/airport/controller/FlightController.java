package airport.controller;

import airport.dto.FlightForm;
import airport.entity.Flight;
import airport.service.*;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
            RunwayService runwayService) {
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
    }

    @GetMapping("/flights")
    public String getFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlightViews());
        return "flights";
    }

    @GetMapping("/flights/{id}")
    public String getFlight(@PathVariable Integer id, Model model) {
        model.addAttribute("flight", flightService.getFlightDetailsView(id));
        return "flight";
    }

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
                    "errorMessage",
                    "Cannot delete flight because it is used by one or more objects."
            );
            redirectAttributes.addFlashAttribute("errorFlightId", id);
        }
        return "redirect:/flights/" + id;
    }
}
