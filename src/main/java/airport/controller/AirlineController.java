package airport.controller;

import airport.dto.AirlineForm;
import airport.service.AirlineService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService service) {
        this.airlineService = service;
    }

    @GetMapping("/airlines")
    public String getAirlines(Model model) {
        model.addAttribute(
                "airlines",
                airlineService.getAllAirlines()
        );
        return "airlines";
    }

    @GetMapping("airlines/{id}")
    public String getAirline(
            @PathVariable Integer id,
            Model model) {
        model.addAttribute(
                "airline",
                airlineService.getAirlineById(id)
        );
        return "airline";
    }

    @GetMapping("/airlines/new")
    public String showCreateAirlineForm(Model model) {
        model.addAttribute("airlineForm", new AirlineForm());
        return "airline-form";
    }

    @PostMapping("/airlines")
    public String createAirline(
            @Valid @ModelAttribute("airlineForm") AirlineForm form,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "airline-form";
        }
        airlineService.saveAirline(form);
        return "redirect:airlines";
    }
}
