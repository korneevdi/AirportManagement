package airport.controller;

import airport.service.CheckInCounterService;
import airport.entity.CheckInCounter;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckInCounterController {

    private final CheckInCounterService checkInCounterService;

    public CheckInCounterController(CheckInCounterService service) {
        this.checkInCounterService = service;
    }

    @GetMapping("/check-in-counters")
    public String getCheckInCounters(Model model) {
        model.addAttribute(
                "checkInCounters",
                checkInCounterService.getAllCheckInCounters()
        );
        return "check-in-counters";
    }

    @GetMapping("/check-in-counters/new")
    public String showCheckInCounterForm(Model model) {
        model.addAttribute("checkInCounter", new CheckInCounter());
        return "check-in-counter-form";
    }

    @PostMapping("/check-in-counters")
    public String createCheckInCounter(
            @Valid @ModelAttribute("checkInCounter") CheckInCounter counter,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "check-in-counter-form";
        }
        checkInCounterService.saveCheckInCounter(counter);
        return "redirect:check-in-counters";
    }
}
