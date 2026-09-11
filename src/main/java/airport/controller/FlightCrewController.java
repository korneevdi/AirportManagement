package airport.controller;

import airport.entity.FlightCrew;
import airport.service.FlightCrewService;
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
public class FlightCrewController {

    private final FlightCrewService flightCrewService;
    private final SexService sexService;

    public FlightCrewController(
            FlightCrewService service,
            SexService sexService) {
        this.flightCrewService = service;
        this.sexService = sexService;
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
                "flightCrew",
                flightCrewService.getFlightCrewById(id)
        );
        return "flight-crew";
    }

    @GetMapping("/flight-crews/new")
    public String showFlightCrewForm(Model model) {
        model.addAttribute("flightCrew", new FlightCrew());
        model.addAttribute("sexes", sexService.getAllSexes());
        return "flight-crew-form";
    }

    @PostMapping("/flight-crews")
    public String createFlightCrew(
            @Valid @ModelAttribute("flightCrew") FlightCrew crew,
            BindingResult bindingResult,
            Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("sexes", sexService.getAllSexes());
            return "flight-crew-form";
        }
        flightCrewService.saveFlightCrew(crew);
        return "redirect:/flight-crews";
    }
}
