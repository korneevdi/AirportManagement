package airport.controller;

import airport.Service.DepartureFlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartureController {

    private final DepartureFlightService departureFlightService;

    public DepartureController(DepartureFlightService service) {
        this.departureFlightService = service;
    }

    @GetMapping("/departures")
    public String getDeparturesPage(Model model) {

        model.addAttribute(
                "departureFlights",
                departureFlightService.getDepartureFlights()
        );

        return "departures";
    }
}
