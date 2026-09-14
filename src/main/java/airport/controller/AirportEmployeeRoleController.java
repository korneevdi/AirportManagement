package airport.controller;

import airport.entity.AirportEmployeeRole;
import airport.service.AirportEmployeeRoleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AirportEmployeeRoleController {

    private final AirportEmployeeRoleService airportEmployeeRoleService;

    public AirportEmployeeRoleController(AirportEmployeeRoleService service) {
        this.airportEmployeeRoleService = service;
    }

    @GetMapping("/airport-employee-roles")
    public String getAirportEmployeeRoles(Model model) {
        model.addAttribute(
                "airportEmployeeRoles",
                airportEmployeeRoleService.getAllAirportEmployeeRoles()
        );
        return "airport-employee-roles";
    }

    @GetMapping("/airport-employee-roles/new")
    public String showAirportEmployeeRoleForm(Model model) {
        model.addAttribute("airportEmployeeRole", new AirportEmployeeRole());
        return "airport-employee-role-form";
    }

    @PostMapping("/airport-employee-roles")
    public String createAirportEmployeeRole(
            @Valid @ModelAttribute("airportEmployeeRole") AirportEmployeeRole role,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "airport-employee-role-form";
        }
        airportEmployeeRoleService.saveAirportEmployeeRole(role);
        return "redirect:airport-employee-roles";
    }
}
