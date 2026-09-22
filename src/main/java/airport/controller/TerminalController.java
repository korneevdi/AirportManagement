package airport.controller;

import airport.entity.Terminal;
import airport.service.TerminalService;
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

        if (terminalService.existsByNumber(terminal.getNumber())) {
            bindingResult.rejectValue(
                    "number",
                    "duplicate",
                    "Terminal with this number already exists"
            );

            return "terminal-form";
        }

        terminalService.saveTerminal(terminal);
        return "redirect:terminals";
    }

    @GetMapping("/terminals/{id}/edit")
    public String showEditTerminalForm(@PathVariable Integer id, Model model) {
        model.addAttribute("terminal", terminalService.getTerminalById(id));
        return "terminal-form";
    }

    @PostMapping("/terminals/{id}")
    public String updateTerminal(
            @PathVariable Integer id,
            @Valid @ModelAttribute("terminal") Terminal terminal,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "terminal-form";
        }

        if (terminalService.existsByNumberAndIdNot(terminal.getNumber(), id)) {
            bindingResult.rejectValue(
                    "number",
                    "duplicate",
                    "Terminal with this number already exists"
            );

            return "terminal-form";
        }

        terminal.setId(id);
        terminalService.saveTerminal(terminal);
        return "redirect:/terminals";
    }

    @PostMapping("/terminals/{id}/delete")
    public String deleteTerminal(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            terminalService.deleteTerminal(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete terminal because it is used by one or more flights."
            );
            redirectAttributes.addFlashAttribute("errorTerminalId", id);
        }
        return "redirect:/terminals";
    }
}
