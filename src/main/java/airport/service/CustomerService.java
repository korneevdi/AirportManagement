package airport.service;

import airport.dto.AirlineForm;
import airport.dto.CustomerForm;
import airport.entity.Airline;
import airport.entity.AirlineContact;
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
        return customerRepository.findAllByOrderByLastNameAsc();
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

    @Transactional
    public void updateCustomer(Integer id, CustomerForm form) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        CustomerContact contact = customer.getContact();

        contact.setEmail(form.getContactEmail());
        contact.setPhone(form.getContactPhone());
        contact.setCity(form.getCity());
        contact.setAddress(form.getAddress());
        contact.setNotes(form.getNotes());

        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setPassCountry(form.getCountry());
        customer.setPassNumber(form.getPassportNumber());

        customerContactRepository.save(contact);
        customerRepository.save(customer);
    }

    public CustomerForm getCustomerFormById(Integer id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        CustomerContact contact = customer.getContact();

        CustomerForm form = new CustomerForm();

        form.setId(customer.getId());
        form.setFirstName(customer.getFirstName());
        form.setLastName(customer.getLastName());
        form.setCountry(customer.getPassCountry());
        form.setPassportNumber(customer.getPassNumber());

        form.setContactEmail(contact.getEmail());
        form.setContactPhone(contact.getPhone());
        form.setCity(contact.getCity());
        form.setAddress(contact.getAddress());
        form.setNotes(contact.getNotes());

        return form;
    }

    @Transactional
    public void deleteCustomer(Integer id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        CustomerContact contact = customer.getContact();

        customerRepository.delete(customer);
        customerContactRepository.delete(contact);
    }

    public boolean existsByPassportCountryAndPassportNumber(String country, String passportNumber) {
        return customerRepository.existsByPassCountryAndPassNumber(country, passportNumber);
    }

    public boolean existsByPassportCountryAndPassportNumberAndIdNot(String country, String passportNumber, Integer id) {
        return customerRepository.existsByPassCountryAndPassNumberAndIdNot(country, passportNumber, id);
    }

    public boolean existsByEmail(String email) {
        return customerContactRepository.existsByEmail(email);
    }

    public boolean existsByEmailAndIdNot(String email, Integer id) {
        return customerContactRepository.existsByEmailAndIdNot(email, id);
    }
}
