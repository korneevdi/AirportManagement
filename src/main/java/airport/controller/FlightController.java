package airport.controller;

import airport.dto.FlightForm;
import airport.entity.Flight;
import airport.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute(
                "flights",
                flightService.getAllFlights()
        );
        return "flights";
    }

    @GetMapping("/flights/{id}")
    public String getFlight(
            @PathVariable Integer id,
            Model model) {
        model.addAttribute(
                "flight",
                flightService.getFlightById(id)
        );
        return "flight";
    }

    @GetMapping("/flights/new")
    public String showFlightForm(Model model) {
        model.addAttribute("flightForm", new FlightForm());
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
            @Valid @ModelAttribute("flightForm") FlightForm form,
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

        flightService.saveFlight(form);
        return "redirect:/flights";
    }
}
