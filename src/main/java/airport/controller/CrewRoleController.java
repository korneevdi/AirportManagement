package airport.controller;

import airport.entity.Type;
import airport.service.CrewRoleService;
import airport.entity.CrewRole;
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
public class CrewRoleController {

    private final CrewRoleService crewRoleService;

    public CrewRoleController(CrewRoleService service) {
        this.crewRoleService = service;
    }

    @GetMapping("/crew-roles")
    public String getCrewRoles(Model model) {
        model.addAttribute(
                "crewRoles",
                crewRoleService.getAllCrewRoles()
        );
        return "crew-roles";
    }

    @GetMapping("/crew-roles/new")
    public String showCreateCrewRoleForm(Model model) {
        model.addAttribute("crewRole", new CrewRole());
        return "crew-role-form";
    }

    @PostMapping("/crew-roles")
    public String createCrewRole(
            @Valid @ModelAttribute("crewRole") CrewRole crewRole,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "crew-role-form";
        }

        if (crewRoleService.existsByName(crewRole.getName())) {
            bindingResult.rejectValue(
                    "name",
                    "duplicate",
                    "Crew role with this name already exists"
            );

            return "crew-role-form";
        }

        crewRoleService.saveCrewRole(crewRole);
        return "redirect:crew-roles";
    }

    @GetMapping("/crew-roles/{id}/edit")
    public String showEditCrewRoleForm(@PathVariable Integer id, Model model) {
        model.addAttribute("crewRole", crewRoleService.getCrewRoleById(id));
        return "crew-role-form";
    }

    @PostMapping("/crew-roles/{id}")
    public String updateCrewRole(
            @PathVariable Integer id,
            @Valid @ModelAttribute("crewRole") CrewRole role,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "crew-role-form";
        }

        if (crewRoleService.existsByNameAndIdNot(role.getName(), id)) {
            bindingResult.rejectValue(
                    "name",
                    "duplicate",
                    "Crew role with this name already exists"
            );

            return "crew-role-form";
        }

        role.setId(id);
        crewRoleService.saveCrewRole(role);
        return "redirect:/crew-roles";
    }

    @PostMapping("/crew-roles/{id}/delete")
    public String deleteCrewRole(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            crewRoleService.deleteCrewRole(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete crew role because it is used by one or more crews."
            );
            redirectAttributes.addFlashAttribute("errorCrewRoleId", id);
        }
        return "redirect:/crew-roles";
    }
}
