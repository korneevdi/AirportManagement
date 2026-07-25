package airport.Service;

import airport.entity.Customer;
import airport.repository.CustomerContactRepository;
import airport.repository.CustomerRepository;
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

    public Customer getCustomer(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow();
    }
}
