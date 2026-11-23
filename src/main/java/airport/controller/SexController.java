package airport.controller;

import airport.service.SexService;
import airport.entity.Sex;
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
public class SexController {

    private final SexService sexService;

    private static final String TEMPLATE_FOLDER = "reference-data/";

    public SexController(SexService service) {
        this.sexService = service;
    }

    @GetMapping("/sexes")
    public String getSexes(Model model) {
        model.addAttribute(
                "sexes",
                sexService.getAllSexes()
        );
        return TEMPLATE_FOLDER + "sexes";
    }

    @GetMapping("/sexes/new")
    public String showCreateSexForm(Model model) {
        model.addAttribute("sex", new Sex());
        return TEMPLATE_FOLDER + "sex-form";
    }

    @PostMapping("/sexes")
    public String createSex(
            @Valid @ModelAttribute("sex") Sex sex,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return TEMPLATE_FOLDER + "sex-form";
        }

        if (sexService.existsByName(sex.getName())) {
            bindingResult.rejectValue(
                    "name",
                    "duplicate",
                    "Sex with this name already exists"
            );

            return TEMPLATE_FOLDER + "sex-form";
        }

        sexService.saveSex(sex);
        return "redirect:sexes";
    }

    @GetMapping("/sexes/{id}/edit")
    public String showEditSexForm(@PathVariable Integer id, Model model) {
        model.addAttribute("sex", sexService.getSexById(id));
        return TEMPLATE_FOLDER + "sex-form";
    }

    @PostMapping("/sexes/{id}")
    public String updateSex(
            @PathVariable Integer id,
            @Valid @ModelAttribute("sex") Sex sex,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return TEMPLATE_FOLDER + "sex-form";
        }

        if (sexService.existsByNameAndIdNot(sex.getName(), id)) {
            bindingResult.rejectValue(
                    "name",
                    "duplicate",
                    "Sex with this name already exists"
            );

            return TEMPLATE_FOLDER + "sex-form";
        }

        sex.setId(id);
        sexService.saveSex(sex);
        return "redirect:/sexes";
    }

    @PostMapping("/sexes/{id}/delete")
    public String deleteSex(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            sexService.deleteSex(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete sex because it is used by one or more persons."
            );
            redirectAttributes.addFlashAttribute("errorSexId", id);
        }
        return "redirect:/sexes";
    }
}
