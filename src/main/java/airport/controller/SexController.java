package airport.controller;

import airport.service.SexService;
import airport.entity.Sex;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/sexes/new")
    public String showCreateSexForm(Model model) {
        model.addAttribute("sex", new Sex());
        return "sex-form";
    }

    @PostMapping("/sexes")
    public String createSex(
            @Valid @ModelAttribute("sex") Sex sex,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "sex-form";
        }
        sexService.saveSex(sex);
        return "redirect:sexes";
    }
}
