package airport.controller;

import airport.Service.RunwayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RunwayController {

    private final RunwayService runwayService;

    public RunwayController(RunwayService service) {
        this.runwayService = service;
    }

    @GetMapping("/runways")
    public String getRunways(Model model) {
        model.addAttribute(
                "runways",
                runwayService.getAllRunways()
        );
        return "runways";
    }
}
