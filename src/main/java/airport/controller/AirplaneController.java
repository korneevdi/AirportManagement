package airport.controller;

import airport.entity.Airplane;
import airport.service.AirlineService;
import airport.service.AirplaneService;
import airport.service.TypeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String showAirplaneForm(Model model) {
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
        airplaneService.saveAirplane(airplane);
        return "redirect:/airplanes";
    }
}
