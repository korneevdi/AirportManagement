package airport.controller;

import airport.service.AirportService;
import airport.entity.Airport;
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

        if (airportService.existsByIata(airport.getIata())) {
            bindingResult.rejectValue(
                    "iata",
                    "duplicate",
                    "Airport with this IATA already exists"
            );
        }

        if (airportService.existsByIcao(airport.getIcao())) {
            bindingResult.rejectValue(
                    "icao",
                    "duplicate",
                    "Airport with this ICAO already exists"
            );
        }

        if (airportService.existsByName(airport.getName())) {
            bindingResult.rejectValue(
                    "name",
                    "duplicate",
                    "Airport with this name already exists"
            );
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("timeZones", ZoneId.getAvailableZoneIds());
            return "airport-form";
        }

        airportService.saveAirport(airport);
        return "redirect:airports";
    }

    @GetMapping("/airports/{id}/edit")
    public String showEditAirportForm(@PathVariable Integer id, Model model) {
        model.addAttribute("airport", airportService.getAirportById(id));
        model.addAttribute("timeZones", ZoneId.getAvailableZoneIds());
        return "airport-form";
    }

    @PostMapping("/airports/{id}")
    public String updateAirport(
            @PathVariable Integer id,
            @Valid @ModelAttribute("airport") Airport airport,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("timeZones", ZoneId.getAvailableZoneIds());
            return "airport-form";
        }

        if (airportService.existsByIataAndIdNot(airport.getIata(), id)) {
            bindingResult.rejectValue(
                    "iata",
                    "duplicate",
                    "Airport with this IATA already exists"
            );
        }

        if (airportService.existsByIcaoAndIdNot(airport.getIcao(), id)) {
            bindingResult.rejectValue(
                    "icao",
                    "duplicate",
                    "Airport with this ICAO already exists"
            );
        }

        if (airportService.existsByNameAndIdNot(airport.getName(), id)) {
            bindingResult.rejectValue(
                    "name",
                    "duplicate",
                    "Airport with this name already exists"
            );
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("timeZones", ZoneId.getAvailableZoneIds());
            return "airport-form";
        }

        airport.setId(id);
        airportService.saveAirport(airport);

        return "redirect:/airports";
    }

    @PostMapping("/airports/{id}/delete")
    public String deleteAirport(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            airportService.deleteAirport(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete airport because it is used by one or more flights."
            );
            redirectAttributes.addFlashAttribute("errorAirportId", id);
        }
        return "redirect:/airports";
    }
}
