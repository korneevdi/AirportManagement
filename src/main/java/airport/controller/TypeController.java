package airport.controller;

import airport.service.TypeService;
import airport.entity.Type;
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

        if (typeService.existsByName(type.getName())) {
            bindingResult.rejectValue(
                    "name",
                    "duplicate",
                    "Type with this name already exists"
            );

            return "type-form";
        }

        typeService.saveType(type);
        return "redirect:types";
    }

    @GetMapping("/types/{id}/edit")
    public String showEditTypeForm(@PathVariable Integer id, Model model) {
        model.addAttribute("type", typeService.getTypeById(id));
        return "type-form";
    }

    @PostMapping("/types/{id}")
    public String updateType(
            @PathVariable Integer id,
            @Valid @ModelAttribute("type") Type type,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "type-form";
        }

        if (typeService.existsByNameAndIdNot(type.getName(), id)) {
            bindingResult.rejectValue(
                    "name",
                    "duplicate",
                    "Type with this name already exists"
            );

            return "type-form";
        }

        type.setId(id);
        typeService.saveType(type);
        return "redirect:/types";
    }

    @PostMapping("/types/{id}/delete")
    public String deleteType(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            typeService.deleteType(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete type because it is used by one or more flights or airplanes."
            );
            redirectAttributes.addFlashAttribute("errorTypeId", id);
        }
        return "redirect:/types";
    }
}
