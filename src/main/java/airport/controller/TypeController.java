package airport.controller;

import airport.Service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
