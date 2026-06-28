package airport.controller;

import airport.Service.AirportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService service) {
        this.airportService = service;
    }

    @GetMapping("/airports")
    public String getAirports(Model model) {

        model.addAttribute(
                "airports",
                airportService.getAllAirports()
        );

        return "airports";
    }
}
