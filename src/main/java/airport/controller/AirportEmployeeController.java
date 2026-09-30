package airport.controller;

import airport.dto.AirportEmployeeForm;
import airport.service.AirportEmployeeRoleService;
import airport.service.AirportEmployeeService;
import airport.service.SexService;
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
public class AirportEmployeeController {

    private final AirportEmployeeService airportEmployeeService;
    private final AirportEmployeeRoleService airportEmployeeRoleService;
    private final SexService sexService;

    public AirportEmployeeController(
            AirportEmployeeService airportEmployeeService,
            AirportEmployeeRoleService airportEmployeeRoleService,
            SexService sexService) {
        this.airportEmployeeService = airportEmployeeService;
        this.airportEmployeeRoleService = airportEmployeeRoleService;
        this.sexService = sexService;
    }

    @GetMapping("/airport-employees")
    public String getAirportEmployees(Model model) {
        model.addAttribute("airportEmployees", airportEmployeeService.getAllAirportEmployees());
        return "airport-employees";
    }

    @GetMapping("/airport-employees/{id}")
    public String getAirportEmployee(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", airportEmployeeService.getAirportEmployeeById(id));
        return "airport-employee";
    }

    @GetMapping("/airport-employees/new")
    public String showAirportEmployeeForm(Model model) {
        model.addAttribute("employee", new AirportEmployeeForm());
        model.addAttribute("roles", airportEmployeeRoleService.getAllAirportEmployeeRoles());
        model.addAttribute("sexes", sexService.getAllSexes());
        return "airport-employee-form";
    }

    @PostMapping("/airport-employees")
    public String createAirportEmployee(
            @Valid @ModelAttribute("employee") AirportEmployeeForm form,
            BindingResult bindingResult,
            Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("roles", airportEmployeeRoleService.getAllAirportEmployeeRoles());
            model.addAttribute("sexes", sexService.getAllSexes());
            return "airport-employee-form";
        }

        if (airportEmployeeService.existsByEmail(form.getContactEmail())) {
            bindingResult.rejectValue(
                    "contactEmail",
                    "duplicate",
                    "Airport employee with this contact email already exists"
            );
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", airportEmployeeRoleService.getAllAirportEmployeeRoles());
            model.addAttribute("sexes", sexService.getAllSexes());
            return "airport-employee-form";
        }

        airportEmployeeService.saveAirportEmployee(form);
        return "redirect:/airport-employees";
    }

    @GetMapping("/airport-employees/{id}/edit")
    public String showEditAirportEmployeeForm(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", airportEmployeeService.getAirportEmployeeFormById(id));
        model.addAttribute("roles", airportEmployeeRoleService.getAllAirportEmployeeRoles());
        model.addAttribute("sexes", sexService.getAllSexes());
        return "airport-employee-form";
    }

    @PostMapping("/airport-employees/{id}")
    public String updateAirportEmployee(
            @PathVariable Integer id,
            @Valid @ModelAttribute("employee") AirportEmployeeForm form,
            BindingResult bindingResult,
            Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("roles", airportEmployeeRoleService.getAllAirportEmployeeRoles());
            model.addAttribute("sexes", sexService.getAllSexes());
            return "airport-employee-form";
        }

        if (airportEmployeeService.existsByEmailAndIdNot(form.getContactEmail(), id)) {
            bindingResult.rejectValue(
                    "contactEmail",
                    "duplicate",
                    "Airport employee with this contact email already exists"
            );
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", airportEmployeeRoleService.getAllAirportEmployeeRoles());
            model.addAttribute("sexes", sexService.getAllSexes());
            return "airport-employee-form";
        }

        airportEmployeeService.updateAirportEmployee(id, form);
        return "redirect:/airport-employees";
    }

    @PostMapping("/airport-employees/{id}/delete")
    public String deleteAirportEmployee(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            airportEmployeeService.deleteAirportEmployee(id);
            return "redirect:/airport-employees";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete airport employee because it is used by one or more flights."
            );
            redirectAttributes.addFlashAttribute("errorAirportEmployeeId", id);
        }
        return "redirect:/airport-employees/" + id;
    }
}
