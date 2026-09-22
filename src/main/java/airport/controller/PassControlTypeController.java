package airport.controller;

import airport.service.PassControlTypeService;
import airport.entity.PassControlType;
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
    public String showCreatePassControlTypeForm(Model model) {
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

    @GetMapping("/pass-control-types/{id}/edit")
    public String showEditPassControlTypeForm(@PathVariable Integer id, Model model) {
        model.addAttribute("passControlType", passControlTypeService.getPassControlTypeDyId(id));
        return "pass-control-type-form";
    }

    @PostMapping("/pass-control-types/{id}")
    public String updatePassControlType(
            @PathVariable Integer id,
            @Valid @ModelAttribute("passControlType") PassControlType passControlType,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "pass-control-type-form";
        }

        passControlType.setId(id);
        passControlTypeService.savePassControlType(passControlType);
        return "redirect:/pass-control-types";
    }

    @PostMapping("/pass-control-types/{id}/delete")
    public String deletePassControlType(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            passControlTypeService.deletePassControlType(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete passport control type because it is used by one or more flights."
            );
            redirectAttributes.addFlashAttribute("errorPassControlTypeId", id);
        }
        return "redirect:/pass-control-types";
    }
}
