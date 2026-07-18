package airport.controller;

import airport.Service.AirlineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService service) {
        this.airlineService = service;
    }

    @GetMapping("/airlines")
    public String getAirlines(Model model) {
        model.addAttribute(
                "airlines",
                airlineService.getAllAirlines()
        );
        return "airlines";
    }

    @GetMapping("airlines/{id}")
    public String getAirline(
            @PathVariable Integer id,
            Model model) {
        model.addAttribute(
                "airline",
                airlineService.getAirline(id)
        );
        return "airline";
    }
}
