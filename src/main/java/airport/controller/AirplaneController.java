package airport.controller;

import airport.Service.AirplaneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AirplaneController {

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService service) {
        this.airplaneService = service;
    }

    @GetMapping("/airplanes")
    public String getAirplanes(Model model) {
        model.addAttribute(
                "airplanes",
                airplaneService.getAllAirplanes()
        );
        return "airplanes";
    }
}
