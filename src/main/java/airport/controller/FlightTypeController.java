package airport.controller;

import airport.Service.FlightTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlightTypeController {

    private final FlightTypeService flightTypeService;

    public FlightTypeController(FlightTypeService service) {
        this.flightTypeService = service;
    }

    @GetMapping("/flight-types")
    public String getFlightTypes(Model model) {
        model.addAttribute(
                "flightTypes",
                flightTypeService.getAllFlightTypes()
        );
        return "flight-types";
    }
}
