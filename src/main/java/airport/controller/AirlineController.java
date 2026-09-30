package airport.controller;

import airport.dto.AirlineForm;
import airport.service.AirlineService;
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

    @GetMapping("/airlines/{id}")
    public String getAirline(@PathVariable Integer id, Model model) {
        model.addAttribute("airline", airlineService.getAirlineById(id));
        return "airline";
    }

    @GetMapping("/airlines/new")
    public String showCreateAirlineForm(Model model) {
        model.addAttribute("airline", new AirlineForm());
        return "airline-form";
    }

    @PostMapping("/airlines")
    public String createAirline(
            @Valid @ModelAttribute("airline") AirlineForm form,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "airline-form";
        }

        if (airlineService.existsByIata(form.getIata())) {
            bindingResult.rejectValue(
                    "iata",
                    "duplicate",
                    "Airline with this IATA already exists"
            );
        }

        if (airlineService.existsByIcao(form.getIcao())) {
            bindingResult.rejectValue(
                    "icao",
                    "duplicate",
                    "Airline with this ICAO already exists"
            );
        }

        if (airlineService.existsByName(form.getName())) {
            bindingResult.rejectValue(
                    "name",
                    "duplicate",
                    "Airline with this name already exists"
            );
        }

        if (airlineService.existsByEmail(form.getContactEmail())) {
            bindingResult.rejectValue(
                    "contactEmail",
                    "duplicate",
                    "Airline with this contact email already exists"
            );
        }

        if (bindingResult.hasErrors()) {
            return "airline-form";
        }

        airlineService.saveAirline(form);
        return "redirect:/airlines";
    }

    @GetMapping("/airlines/{id}/edit")
    public String showEditAirlineForm(@PathVariable Integer id, Model model) {
        model.addAttribute("airline", airlineService.getAirlineFormById(id));
        return "airline-form";
    }

    @PostMapping("/airlines/{id}")
    public String updateAirline(
            @PathVariable Integer id,
            @Valid @ModelAttribute("airline") AirlineForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "airline-form";
        }

        if (airlineService.existsByIataAndIdNot(form.getIata(), id)) {
            bindingResult.rejectValue(
                    "iata",
                    "duplicate",
                    "Airline with this IATA already exists"
            );
        }

        if (airlineService.existsByIcaoAndIdNot(form.getIcao(), id)) {
            bindingResult.rejectValue(
                    "icao",
                    "duplicate",
                    "Airline with this ICAO already exists"
            );
        }

        if (airlineService.existsByNameAndIdNot(form.getName(), id)) {
            bindingResult.rejectValue(
                    "name",
                    "duplicate",
                    "Airline with this name already exists"
            );
        }

        if (airlineService.existsByEmailAndIdNot(form.getContactEmail(), id)) {
            bindingResult.rejectValue(
                    "contactEmail",
                    "duplicate",
                    "Airline with this contact email already exists"
            );
        }

        if (bindingResult.hasErrors()) {
            return "airline-form";
        }

        airlineService.updateAirline(id, form);

        return "redirect:/airlines";
    }

    @PostMapping("/airlines/{id}/delete")
    public String deleteAirline(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            airlineService.deleteAirline(id);
            return "redirect:/airlines";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete airline because it is used by one or more flights or airplanes."
            );
            redirectAttributes.addFlashAttribute("errorAirlineId", id);
        }
        return "redirect:/airlines/" + id;
    }
}
