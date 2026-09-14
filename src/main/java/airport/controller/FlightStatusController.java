package airport.controller;

import airport.service.FlightStatusService;
import airport.entity.FlightStatus;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

        if(bindingResult.hasErrors()) {
            return "flight-status-form";
        }
        flightStatusService.saveFlightStatus(status);
        return "redirect:flight-statuses";
    }
}
