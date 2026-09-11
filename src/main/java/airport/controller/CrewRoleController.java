package airport.controller;

import airport.service.CrewRoleService;
import airport.entity.CrewRole;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String showCrewRoleForm(Model model) {
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
        crewRoleService.saveCrewRole(crewRole);
        return "redirect:crew-roles";
    }
}
