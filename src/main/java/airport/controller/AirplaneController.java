package airport.controller;

import airport.entity.Airplane;
import airport.service.AirlineService;
import airport.service.AirplaneService;
import airport.service.TypeService;
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
public class AirplaneController {

    private final AirplaneService airplaneService;
    private final AirlineService airlineService;
    private final TypeService typeService;

    public AirplaneController(
            AirplaneService airplaneService,
            AirlineService airlineService,
            TypeService typeService) {
        this.airplaneService = airplaneService;
        this.airlineService = airlineService;
        this.typeService = typeService;
    }

    @GetMapping("/airplanes")
    public String getAirplanes(Model model) {
        model.addAttribute(
                "airplanes",
                airplaneService.getAllAirplanes()
        );
        return "airplanes";
    }

    @GetMapping("/airplanes/new")
    public String showCreateAirplaneForm(Model model) {
        model.addAttribute("airplane", new Airplane());
        model.addAttribute("airlines", airlineService.getAllAirlines());
        model.addAttribute("types", typeService.getAllTypes());
        return "airplane-form";
    }

    @PostMapping("/airplanes")
    public String createAirplane(
            @Valid @ModelAttribute("airplane") Airplane airplane,
            BindingResult bindingResult,
            Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("airlines", airlineService.getAllAirlines());
            model.addAttribute("types", typeService.getAllTypes());
            return "airplane-form";
        }

        if (airplaneService.existsByRegistrationNumber(
                airplane.getRegistrationNumber())) {
            bindingResult.rejectValue(
                    "registrationNumber",
                    "duplicate",
                    "Airplane with this registration number already exists"
            );
        }

        if(bindingResult.hasErrors()) {
            model.addAttribute("airlines", airlineService.getAllAirlines());
            model.addAttribute("types", typeService.getAllTypes());
            return "airplane-form";
        }

        airplaneService.saveAirplane(airplane);
        return "redirect:/airplanes";
    }

    @GetMapping("/airplanes/{id}/edit")
    public String showEditAirplaneForm(@PathVariable Integer id, Model model) {
        model.addAttribute("airplane", airplaneService.getAirplaneById(id));
        model.addAttribute("airlines", airlineService.getAllAirlines());
        model.addAttribute("types", typeService.getAllTypes());
        return "airplane-form";
    }

    @PostMapping("/airplanes/{id}")
    public String updateAirplane(
            @PathVariable Integer id,
            @Valid @ModelAttribute("airplane") Airplane airplane,
            BindingResult bindingResult,
            Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("airlines", airlineService.getAllAirlines());
            model.addAttribute("types", typeService.getAllTypes());
            return "airplane-form";
        }

        if (airplaneService.existsByRegistrationNumberAndIdNot(
                airplane.getRegistrationNumber(), id)) {
            bindingResult.rejectValue(
                    "registrationNumber",
                    "duplicate",
                    "Airplane with this registration number already exists"
            );
        }

        if(bindingResult.hasErrors()) {
            model.addAttribute("airlines", airlineService.getAllAirlines());
            model.addAttribute("types", typeService.getAllTypes());
            return "airplane-form";
        }

        airplaneService.updateAirplane(airplane, id);

        return "redirect:/airplanes";
    }

    @PostMapping("/airplanes/{id}/delete")
    public String deleteAirplane(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            airplaneService.deleteAirplane(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete airplane because it is used by one or more flights."
            );
            redirectAttributes.addFlashAttribute("errorAirplaneId", id);
        }
        return "redirect:/airplanes";
    }
}
