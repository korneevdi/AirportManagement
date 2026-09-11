package airport.service;

import airport.dto.CustomerForm;
import airport.entity.Customer;
import airport.entity.CustomerContact;
import airport.repository.CustomerContactRepository;
import airport.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerContactRepository customerContactRepository;

    public CustomerService(
            CustomerRepository customerRepository,
            CustomerContactRepository customerContactRepository
    ) {
        this.customerRepository = customerRepository;
        this.customerContactRepository = customerContactRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow();
    }

    @Transactional
    public void saveCustomer(CustomerForm form) {

        CustomerContact contact = new CustomerContact();
        contact.setEmail(form.getContactEmail());
        contact.setPhone(form.getContactPhone());
        contact.setCity(form.getCity());
        contact.setAddress(form.getAddress());
        contact.setNotes(form.getNotes());
        customerContactRepository.save(contact);

        Customer customer = new Customer();
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setPassCountry(form.getCountry());
        customer.setPassNumber(form.getPassportNumber());
        customer.setContact(contact);
        customerRepository.save(customer);
    }
}
