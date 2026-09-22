package airport.controller;

import airport.entity.Runway;
import airport.service.RunwayService;
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

        if (runwayService.existsByNumber(runway.getNumber())) {
            bindingResult.rejectValue(
                    "number",
                    "duplicate",
                    "Runway with this number already exists"
            );

            return "runway-form";
        }

        runwayService.saveRunway(runway);
        return "redirect:runways";
    }

    @GetMapping("/runways/{id}/edit")
    public String showEditRunwayForm(@PathVariable Integer id, Model model) {
        model.addAttribute("runway", runwayService.getRunwayById(id));
        return "runway-form";
    }

    @PostMapping("/runways/{id}")
    public String updateRunway(
            @PathVariable Integer id,
            @Valid @ModelAttribute("runway") Runway runway,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "runway-form";
        }

        if (runwayService.existsByNumberAndIdNot(runway.getNumber(), id)) {
            bindingResult.rejectValue(
                    "number",
                    "duplicate",
                    "Runway with this number already exists"
            );

            return "runway-form";
        }

        runway.setId(id);
        runwayService.saveRunway(runway);
        return "redirect:/runways";
    }

    @PostMapping("runways/{id}/delete")
    public String deleteRunway(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            runwayService.deleteRunway(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete runway because it is used by one or more flights.");
            redirectAttributes.addFlashAttribute("errorRunwayId", id);
        }
        return "redirect:/runways";
    }
}
