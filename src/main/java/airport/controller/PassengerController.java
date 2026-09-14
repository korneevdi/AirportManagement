package airport.controller;

import airport.entity.Passenger;
import airport.service.PassengerService;
import airport.service.SexService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PassengerController {

    private final PassengerService passengerService;
    private final SexService sexService;

    public PassengerController(
            PassengerService passengerService,
            SexService sexService) {
        this.passengerService = passengerService;
        this.sexService = sexService;
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
                passengerService.getPassengerById(id)
        );
        return "passenger";
    }

    @GetMapping("/passengers/new")
    public String showPassengerForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        model.addAttribute("sexes", sexService.getAllSexes());
        return "passenger-form";
    }

    @PostMapping("/passengers")
    public String createPassenger(
            @Valid @ModelAttribute("passenger") Passenger passenger,
            BindingResult bindingResult,
            Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("sexes", sexService.getAllSexes());
            return "passenger-form";
        }
        passengerService.savePassenger(passenger);
        return "redirect:/passengers";
    }
}
