package airport.controller;

import airport.Service.SexService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SexController {

    private final SexService sexService;

    public SexController(SexService service) {
        this.sexService = service;
    }

    @GetMapping("/sexes")
    public String getSexes(Model model) {
        model.addAttribute(
                "sexes",
                sexService.getAllSexes()
        );
        return "sexes";
    }
}
