package airport.controller;

import airport.Service.ArrivalFlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArrivalController {

    private final ArrivalFlightService arrivalFlightService;

    public ArrivalController(ArrivalFlightService service) {
        this.arrivalFlightService = service;
    }

    @GetMapping("/arrivals")
    public String getArrivalsPage(Model model) {

        model.addAttribute(
                "arrivalFlights",
                arrivalFlightService.getArrivalFlights()
        );

        return "arrivals";
    }
}
