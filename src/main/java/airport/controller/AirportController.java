package airport.controller;

import airport.Service.AirportService;
import airport.entity.Airport;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.ZoneId;

@Controller
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService service) {
        this.airportService = service;
    }

    @GetMapping("/airports")
    public String getAirports(Model model) {

        model.addAttribute(
                "airports",
                airportService.getAllAirports()
        );

        return "airports";
    }

    @GetMapping("/airports/new")
    public String showCreateAirportForm(Model model) {
        model.addAttribute("airport", new Airport());
        model.addAttribute("timeZones", ZoneId.getAvailableZoneIds());
        return "airport-form";
    }

    @PostMapping("/airports")
    public String createAirport(
            @Valid @ModelAttribute("airport") Airport airport,
            BindingResult bindingResult,
            Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("timeZones", ZoneId.getAvailableZoneIds());
            return "airport-form";
        }
        airportService.saveAirport(airport);
        return "redirect:airports";
    }
}
