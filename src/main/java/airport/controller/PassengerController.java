package airport.controller;

import airport.entity.Passenger;
import airport.service.PassengerService;
import airport.service.SexService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String showCreatePassengerForm(Model model) {
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

        if (passengerService.existsByPassportCountryAndPassportNumber(
                passenger.getPassCountry(),
                passenger.getPassNumber())) {
            bindingResult.rejectValue(
                    "passNumber",
                    "duplicate",
                    "Passenger with this passport already exists"
            );
        }

        if(bindingResult.hasErrors()) {
            model.addAttribute("sexes", sexService.getAllSexes());
            return "passenger-form";
        }

        passengerService.savePassenger(passenger);
        return "redirect:/passengers";
    }

    @GetMapping("/passengers/{id}/edit")
    public String showEditPassengerForm(@PathVariable Integer id, Model model) {
        model.addAttribute("passenger", passengerService.getPassengerById(id));
        model.addAttribute("sexes", sexService.getAllSexes());
        return "passenger-form";
    }

    @PostMapping("/passengers/{id}")
    public String updatePassenger(
            @PathVariable Integer id,
            @Valid @ModelAttribute("passenger") Passenger passenger,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("sexes", sexService.getAllSexes());
            return "passenger-form";
        }

        if (passengerService.existsByPassportCountryAndPassportNumberAndIdNot(
                passenger.getPassCountry(), passenger.getPassNumber(), id)) {
            bindingResult.rejectValue(
                    "passNumber",
                    "duplicate",
                    "Passenger with this passport already exists"
            );
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("sexes", sexService.getAllSexes());
            return "passenger-form";
        }

        passengerService.updatePassenger(passenger, id);

        return "redirect:/passengers";
    }

    @PostMapping("/passengers/{id}/delete")
    public String deletePassenger(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            passengerService.deletePassenger(id);
            return "redirect:/passengers";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete passenger because it is used by one or more flights."
            );
            redirectAttributes.addFlashAttribute("errorPassengerId", id);
        }
        return "redirect:/passengers/" + id;
    }
}
