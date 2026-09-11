package airport.controller;

import airport.entity.Terminal;
import airport.service.TerminalService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/terminals/new")
    public String showTerminalForm(Model model) {
        model.addAttribute("terminal", new Terminal());
        return "terminal-form";
    }

    @PostMapping("/terminals")
    public String createTerminal(
            @Valid @ModelAttribute("terminal") Terminal terminal,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "terminal-form";
        }
        terminalService.saveTerminal(terminal);
        return "redirect:terminals";
    }
}
