package airport.controller;

import airport.Service.CrewRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
