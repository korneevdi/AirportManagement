package airport.controller;

import airport.dto.AirlineForm;
import airport.dto.CustomerForm;
import airport.service.CustomerService;
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
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService service) {
        this.customerService = service;
    }

    @GetMapping("/customers")
    public String getCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @GetMapping("/customers/{id}")
    public String getCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer";
    }

    @GetMapping("/customers/new")
    public String showCreateCustomerForm(Model model) {
        model.addAttribute("customer", new CustomerForm());
        return "customer-form";
    }

    @PostMapping("/customers")
    public String createCustomer(
            @Valid @ModelAttribute("customer") CustomerForm form,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "customer-form";
        }

        if (customerService.existsByPassportCountryAndPassportNumber(
                form.getCountry(),
                form.getPassportNumber())) {

            bindingResult.rejectValue(
                    "passportNumber",
                    "duplicate",
                    "Customer with this passport already exists"
            );
        }

        if (customerService.existsByEmail(form.getContactEmail())) {
            bindingResult.rejectValue(
                    "contactEmail",
                    "duplicate",
                    "Customer with this contact email already exists"
            );
        }

        if (bindingResult.hasErrors()) {
            return "customer-form";
        }

        customerService.saveCustomer(form);
        return "redirect:/customers";
    }

    @GetMapping("/customers/{id}/edit")
    public String showEditCustomerForm(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getCustomerFormById(id));
        return "customer-form";
    }

    @PostMapping("/customers/{id}")
    public String updateCustomer(
            @PathVariable Integer id,
            @Valid @ModelAttribute("customer") CustomerForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "customer-form";
        }

        if (customerService.existsByPassportCountryAndPassportNumberAndIdNot(
                form.getCountry(), form.getPassportNumber(), id)) {

            bindingResult.rejectValue(
                    "passportNumber",
                    "duplicate",
                    "Customer with this passport already exists"
            );
        }

        if (customerService.existsByEmailAndIdNot(form.getContactEmail(), id)) {
            bindingResult.rejectValue(
                    "contactEmail",
                    "duplicate",
                    "Customer with this contact email already exists"
            );
        }

        if (bindingResult.hasErrors()) {
            return "customer-form";
        }

        customerService.updateCustomer(id, form);

        return "redirect:/customers";
    }

    @PostMapping("/customers/{id}/delete")
    public String deleteCustomer(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            customerService.deleteCustomer(id);
            return "redirect:/customers";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete customer because it is used by one or more flights."
            );
            redirectAttributes.addFlashAttribute("errorCustomerId", id);
        }
        return "redirect:/customers/" + id;
    }
}
