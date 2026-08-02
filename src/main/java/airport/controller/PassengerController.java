package airport.controller;

import airport.Service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/passengers/{id}")
    public String getPassenger(
            @PathVariable Integer id,
            Model model) {
        model.addAttribute(
                "passenger",
                passengerService.getPassenger(id)
        );
        return "passenger";
    }
}
