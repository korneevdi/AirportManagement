package airport.controller;

import airport.service.GateService;
import airport.entity.Gate;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GateController {

    private final GateService gateService;

    public GateController(GateService service) {
        this.gateService = service;
    }

    @GetMapping("/gates")
    public String getGates(Model model) {
        model.addAttribute(
                "gates",
                gateService.getAllGates()
        );
        return "gates";
    }

    @GetMapping("/gates/new")
    public String showGateForm(Model model) {
        model.addAttribute("gate", new Gate());
        return "gate-form";
    }

    @PostMapping("/gates")
    public String createGate(
            @Valid @ModelAttribute("gate") Gate gate,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "gate-form";
        }
        gateService.saveGate(gate);
        return "redirect:gates";
    }
}
