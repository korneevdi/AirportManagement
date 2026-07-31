package airport.controller;

import airport.Service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService service) {
        this.flightService = service;
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
                flightService.getFlight(id)
        );
        return "flight";
    }
}
