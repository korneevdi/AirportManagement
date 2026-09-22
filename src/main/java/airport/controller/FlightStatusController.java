package airport.controller;

import airport.service.FlightStatusService;
import airport.entity.FlightStatus;
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
public class FlightStatusController {

    private final FlightStatusService flightStatusService;

    public FlightStatusController(FlightStatusService service) {
        this.flightStatusService = service;
    }

    @GetMapping("/flight-statuses")
    public String getFlightStatuses(Model model) {
        model.addAttribute(
                "flightStatuses",
                flightStatusService.getAllFlightStatuses()
        );
        return "flight-statuses";
    }

    @GetMapping("/flight-statuses/new")
    public String showCreateFlightStatusForm(Model model) {
        model.addAttribute("flightStatus", new FlightStatus());
        return "flight-status-form";
    }

    @PostMapping("/flight-statuses")
    public String createFlightStatus(
            @Valid @ModelAttribute("flightStatus") FlightStatus status,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "flight-status-form";
        }

        if (flightStatusService.existsByName(status.getName())) {
            bindingResult.rejectValue(
                    "name",
                    "duplicate",
                    "Flight status with this name already exists"
            );

            return "flight-status-form";
        }

        flightStatusService.saveFlightStatus(status);
        return "redirect:flight-statuses";
    }

    @GetMapping("/flight-statuses/{id}/edit")
    public String showEditFlightStatusForm(@PathVariable Integer id, Model model) {
        model.addAttribute("flightStatus", flightStatusService.getFlightStatusById(id));
        return "flight-status-form";
    }

    @PostMapping("/flight-statuses/{id}")
    public String updateFlightStatus(
            @PathVariable Integer id,
            @Valid @ModelAttribute("flightStatus") FlightStatus status,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "flight-status-form";
        }

        if (flightStatusService.existsByNameAndIdNot(status.getName(), id)) {
            bindingResult.rejectValue(
                    "name",
                    "duplicate",
                    "Flight status with this name already exists"
            );

            return "flight-status-form";
        }

        status.setId(id);
        flightStatusService.saveFlightStatus(status);
        return "redirect:/flight-statuses";
    }

    @PostMapping("/flight-statuses/{id}/delete")
    public String deleteFlightStatus(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try {
            flightStatusService.deleteFlightStatus(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete status because it is used by one or more flights."
            );
            redirectAttributes.addFlashAttribute("errorStatusId", id);
        }
        return "redirect:/flight-statuses";
    }
}
