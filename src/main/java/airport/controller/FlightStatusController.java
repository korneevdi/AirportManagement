package airport.controller;

import airport.Service.FlightStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlightStatusController {

    private final FlightStatusService flightStatusService;

    public FlightStatusController(FlightStatusService service) {
        this.flightStatusService = service;
    }

    @GetMapping("/flight-statuses")
    public String getFlightStatuses(Model model) {
        model.addAttribute(
                "flightStatuses",
                flightStatusService.getAllFlightStatuses()
        );
        return "flight-statuses";
    }
}
