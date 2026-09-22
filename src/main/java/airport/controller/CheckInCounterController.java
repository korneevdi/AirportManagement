package airport.controller;

import airport.service.CheckInCounterService;
import airport.entity.CheckInCounter;
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
    public String showCreateCheckInCounterForm(Model model) {
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

        if (checkInCounterService.existsByNumber(counter.getNumber())) {
            bindingResult.rejectValue(
                    "number",
                    "duplicate",
                    "Check-in counter with this number already exists"
            );

            return "check-in-counter-form";
        }

        checkInCounterService.saveCheckInCounter(counter);
        return "redirect:check-in-counters";
    }

    @GetMapping("/check-in-counters/{id}/edit")
    public String showEditCrewRoleForm(@PathVariable Integer id, Model model) {
        model.addAttribute("checkInCounter", checkInCounterService.getCheckInCounterById(id));
        return "check-in-counter-form";
    }

    @PostMapping("/check-in-counters/{id}")
    public String updateCheckInCounter(
            @PathVariable Integer id,
            @Valid @ModelAttribute("checkInCounter") CheckInCounter counter,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "check-in-counter-form";
        }

        if (checkInCounterService.existsByNumberAndIdNot(counter.getNumber(), id)) {
            bindingResult.rejectValue(
                    "number",
                    "duplicate",
                    "Check-in counter with this number already exists"
            );

            return "check-in-counter-form";
        }

        counter.setId(id);
        checkInCounterService.saveCheckInCounter(counter);
        return "redirect:/check-in-counters";
    }

    @PostMapping("/check-in-counters/{id}/delete")
    public String deleteCheckInCounter(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            checkInCounterService.deleteCheckInCounter(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete check-in counter because it is used by one or more flights."
            );
            redirectAttributes.addFlashAttribute("errorCheckInCounterId", id);
        }
        return "redirect:/check-in-counters";
    }
}
