package airport.controller;

import airport.Service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService service) {
        this.passengerService = service;
    }

    @GetMapping("/passengers")
    public String getPassengers(Model model) {
        model.addAttribute(
                "passengers",
                passengerService.getAllPassengers()
        );
        return "passengers";
    }
}
