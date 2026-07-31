package airport.controller;

import airport.Service.PassControlTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
