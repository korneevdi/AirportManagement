package airport.controller;

import airport.service.TypeService;
import airport.entity.Type;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService service) {
        this.typeService = service;
    }

    @GetMapping("/types")
    public String getTypes(Model model) {
        model.addAttribute(
                "types",
                typeService.getAllTypes()
        );
        return "types";
    }

    @GetMapping("/types/new")
    public String showCreateTypeForm(Model model) {
        model.addAttribute("type", new Type());
        return "type-form";
    }

    @PostMapping("/types")
    public String createType(
            @Valid @ModelAttribute("type") Type type,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "type-form";
        }
        typeService.saveType(type);
        return "redirect:types";
    }
}
