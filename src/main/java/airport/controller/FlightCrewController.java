package airport.controller;

import airport.Service.FlightCrewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FlightCrewController {

    private final FlightCrewService flightCrewService;

    public FlightCrewController(FlightCrewService service) {
        this.flightCrewService = service;
    }

    @GetMapping("/flight-crews")
    public String getFlightCrews(Model model) {
        model.addAttribute(
                "flightCrews",
                flightCrewService.getAllFlightCrews()
        );
        return "flight-crews";
    }

    @GetMapping("/flight-crews/{id}")
    public String getFlightCrew(
            @PathVariable Integer id,
            Model model) {
        model.addAttribute(
                "flight",
                flightCrewService.getFlightCrew(id)
        );
        return "flight-crew";
    }
}
