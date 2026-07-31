package airport.controller;

import airport.Service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService service) {
        this.customerService = service;
    }

    @GetMapping("/customers")
    public String getCustomers(Model model) {
        model.addAttribute(
                "customers",
                customerService.getAllCustomers()
        );
        return "customers";
    }

    @GetMapping("/customers/{id}")
    public String getCustomer(
            @PathVariable Integer id,
            Model model) {
        model.addAttribute(
                "customer",
                customerService.getCustomer(id)
        );
        return "customer";
    }
}
