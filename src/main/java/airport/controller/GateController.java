package airport.controller;

import airport.service.GateService;
import airport.entity.Gate;
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
public class GateController {

    private final GateService gateService;

    private static final String TEMPLATE_FOLDER = "reference-data/";

    public GateController(GateService service) {
        this.gateService = service;
    }

    @GetMapping("/gates")
    public String getGates(Model model) {
        model.addAttribute(
                "gates",
                gateService.getAllGates()
        );
        return TEMPLATE_FOLDER + "gates";
    }

    @GetMapping("/gates/new")
    public String showGateForm(Model model) {
        model.addAttribute("gate", new Gate());
        return TEMPLATE_FOLDER + "gate-form";
    }

    @PostMapping("/gates")
    public String createGate(
            @Valid @ModelAttribute("gate") Gate gate,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return TEMPLATE_FOLDER + "gate-form";
        }

        if (gateService.existsByNumber(gate.getNumber())) {
            bindingResult.rejectValue(
                    "number",
                    "duplicate",
                    "Gate with this number already exists"
            );

            return TEMPLATE_FOLDER + "gate-form";
        }

        gateService.saveGate(gate);
        return "redirect:gates";
    }

    @GetMapping("/gates/{id}/edit")
    public String showEditGateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("gate", gateService.getGateById(id));
        return TEMPLATE_FOLDER + "gate-form";
    }

    @PostMapping("/gates/{id}")
    public String updateGate(
            @PathVariable Integer id,
            @Valid @ModelAttribute("gate") Gate gate,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return TEMPLATE_FOLDER + "gate-form";
        }

        if (gateService.existsByNumberAndIdNot(gate.getNumber(), id)) {
            bindingResult.rejectValue(
                    "number",
                    "duplicate",
                    "Gate with this number already exists"
            );

            return TEMPLATE_FOLDER + "gate-form";
        }

        gate.setId(id);
        gateService.saveGate(gate);
        return "redirect:/gates";
    }

    @PostMapping("/gates/{id}/delete")
    public String deleteGate(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            gateService.deleteGate(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete gate because it is used by one or more flights.");
            redirectAttributes.addFlashAttribute("errorGateId", id);
        }
        return "redirect:/gates";
    }
}
