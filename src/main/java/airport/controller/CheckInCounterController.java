package airport.controller;

import airport.Service.CheckInCounterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
