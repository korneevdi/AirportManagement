package airport.controller;

import airport.dto.CustomerForm;
import airport.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
                customerService.getCustomerById(id)
        );
        return "customer";
    }

    @GetMapping("/customers/new")
    public String showCreateCustomerForm(Model model) {
        model.addAttribute("customerForm", new CustomerForm());
        return "customer-form";
    }

    @PostMapping("/customers")
    public String createCustomer(
            @Valid @ModelAttribute("customerForm") CustomerForm form,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "customer-form";
        }
        customerService.saveCustomer(form);
        return "redirect:customers";
    }
}
