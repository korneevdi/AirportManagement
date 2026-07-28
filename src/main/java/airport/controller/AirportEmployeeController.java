package airport.controller;

import airport.Service.AirportEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AirportEmployeeController {

    private final AirportEmployeeService airportEmployeeService;

    public AirportEmployeeController(AirportEmployeeService service) {
        this.airportEmployeeService = service;
    }

    @GetMapping("/airport-employees")
    public String getAirportEmployees(Model model) {
        model.addAttribute(
                "airportEmployees",
                airportEmployeeService.getAllAirportEmployees()
        );
        return "airport-employees";
    }

    @GetMapping("/airport-employees/{id}")
    public String getAirportEmployee(
            @PathVariable Integer id,
            Model model) {
        model.addAttribute(
                "employee",
                airportEmployeeService.getAirportEmployee(id)
        );
        return "airport-employee";
    }
}
