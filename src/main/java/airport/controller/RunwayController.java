package airport.controller;

import airport.entity.Runway;
import airport.service.RunwayService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/runways/new")
    public String showRunwayForm(Model model) {
        model.addAttribute("runway", new Runway());
        return "runway-form";
    }

    @PostMapping("runways")
    public String createRunway(
            @Valid @ModelAttribute("runway") Runway runway,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "runway-form";
        }
        runwayService.saveRunway(runway);
        return "redirect:runways";
    }
}
