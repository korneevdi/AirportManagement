package airport.controller;

import airport.Service.GateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
