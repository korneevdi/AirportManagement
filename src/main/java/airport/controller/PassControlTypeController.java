package airport.controller;

import airport.service.PassControlTypeService;
import airport.entity.PassControlType;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PassControlTypeController {

    private final PassControlTypeService passControlTypeService;

    public PassControlTypeController(PassControlTypeService service) {
        this.passControlTypeService = service;
    }

    @GetMapping("/pass-control-types")
    public String getPassControlTypes(Model model) {
        model.addAttribute(
                "passControlTypes",
                passControlTypeService.getAllPassControlTypes()
        );
        return "pass-control-types";
    }

    @GetMapping("/pass-control-types/new")
    public String showPassControlTypeForm(Model model) {
        model.addAttribute("passControlType", new PassControlType());
        return "pass-control-type-form";
    }

    @PostMapping("/pass-control-types")
    public String createPassControlType(
            @Valid @ModelAttribute("passControlType") PassControlType passControlType,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "pass-control-type-form";
        }
        passControlTypeService.savePassControlType(passControlType);
        return "redirect:pass-control-types";
    }
}
