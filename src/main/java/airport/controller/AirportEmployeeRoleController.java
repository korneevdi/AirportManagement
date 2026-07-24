package airport.controller;

import airport.Service.AirportEmployeeRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
