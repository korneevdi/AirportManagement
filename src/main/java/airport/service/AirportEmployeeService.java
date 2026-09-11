package airport.service;

import airport.dto.AirportEmployeeForm;
import airport.entity.AirportEmployee;
import airport.entity.AirportEmployeeContact;
import airport.entity.AirportEmployeeEmergencyContact;
import airport.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportEmployeeService {

    private final AirportEmployeeRepository airportEmployeeRepository;

    private final AirportEmployeeRoleRepository airportEmployeeRoleRepository;

    private final SexRepository sexRepository;

    private final AirportEmployeeContactRepository airportEmployeeContactRepository;

    private final AirportEmployeeEmergencyContactRepository airportEmployeeEmergencyContactRepository;

    public AirportEmployeeService(
            AirportEmployeeRepository airportEmployeeRepository,
            AirportEmployeeRoleRepository airportEmployeeRoleRepository,
            SexRepository sexRepository,
            AirportEmployeeContactRepository airportEmployeeContactRepository,
            AirportEmployeeEmergencyContactRepository airportEmployeeEmergencyContactRepository) {
        this.airportEmployeeRepository = airportEmployeeRepository;
        this.airportEmployeeRoleRepository = airportEmployeeRoleRepository;
        this.sexRepository = sexRepository;
        this.airportEmployeeContactRepository = airportEmployeeContactRepository;
        this.airportEmployeeEmergencyContactRepository = airportEmployeeEmergencyContactRepository;
    }

    public List<AirportEmployee> getAllAirportEmployees() {
        return airportEmployeeRepository.findAll();
    }

    public AirportEmployee getAirportEmployeeById(Integer id) {
        return airportEmployeeRepository.findById(id)
                .orElseThrow();
    }

    public List<AirportEmployee> getAllDispatchers() {
        return airportEmployeeRepository.findByRoleName("Dispatcher");
    }

    @Transactional
    public void saveAirportEmployee(AirportEmployeeForm form) {

        AirportEmployeeContact contact = new AirportEmployeeContact();
        contact.setEmail(form.getContactEmail());
        contact.setPhone(form.getContactPhone());
        contact.setCity(form.getCity());
        contact.setAddress(form.getAddress());
        contact.setNotes(form.getNotes());
        airportEmployeeContactRepository.save(contact);

        AirportEmployeeEmergencyContact emergencyContact = new AirportEmployeeEmergencyContact();
        emergencyContact.setName(form.getEmergencyContactName());
        emergencyContact.setRelation(form.getEmergencyContactRelation());
        emergencyContact.setPhone(form.getEmergencyContactPhone());
        airportEmployeeEmergencyContactRepository.save(emergencyContact);

        AirportEmployee employee = new AirportEmployee();
        employee.setFirstName(form.getFirstName());
        employee.setLastName(form.getLastName());
        employee.setRole(form.getRole());
        employee.setSex(form.getSex());
        employee.setBirthDate(form.getBirthDate());
        employee.setPassCountry(form.getCountry());
        employee.setPassNumber(form.getPassportNumber());
        employee.setContact(contact);
        employee.setEmergencyContact(emergencyContact);
        airportEmployeeRepository.save(employee);
    }
}
