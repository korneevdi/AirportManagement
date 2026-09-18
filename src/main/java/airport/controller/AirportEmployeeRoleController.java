package airport.controller;

import airport.entity.AirportEmployeeRole;
import airport.service.AirportEmployeeRoleService;
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
    public String showCreateAirportEmployeeRoleForm(Model model) {
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

    @GetMapping("airport-employee-roles/{id}/edit")
    public String showEditAirportEmployeeRoleForm(@PathVariable Integer id, Model model) {
        model.addAttribute("airportEmployeeRole", airportEmployeeRoleService.getAirportEmployeeRoleById(id));
        return "airport-employee-role-form";
    }

    @PostMapping("airport-employee-roles/{id}")
    public String updateAirportEmployeeRole(
            @PathVariable Integer id,
            @Valid @ModelAttribute("airportEmployeeRole") AirportEmployeeRole role,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "airport-employee-role-form";
        }

        role.setId(id);
        airportEmployeeRoleService.saveAirportEmployeeRole(role);
        return "redirect:/airport-employee-roles";
    }

    @PostMapping("airport-employee-roles/{id}/delete")
    public String deleteAirportEmployeeRole(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            airportEmployeeRoleService.deleteAirportEmployeeRole(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete role because it is used by one or more airport employee.");
            redirectAttributes.addFlashAttribute("errorAirportEmployeeRoleId", id);
        }
        return "redirect:/airport-employee-roles";
    }
}
