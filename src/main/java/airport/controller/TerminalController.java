package airport.controller;

import airport.Service.TerminalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TerminalController {

    private final TerminalService terminalService;

    public TerminalController(TerminalService service) {
        this.terminalService = service;
    }

    @GetMapping("/terminals")
    public String getTerminals(Model model) {
        model.addAttribute(
                "terminals",
                terminalService.getAllTerminals()
        );
        return "terminals";
    }
}
