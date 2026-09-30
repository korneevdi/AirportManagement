package airport.service;

import airport.dto.AirportEmployeeForm;
import airport.dto.CustomerForm;
import airport.entity.*;
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
        return airportEmployeeRepository.findAllByOrderByLastNameAsc();
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

    @Transactional
    public void updateAirportEmployee(Integer id, AirportEmployeeForm form) {

        AirportEmployee employee = airportEmployeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airport employee not found"));

        AirportEmployeeContact contact = employee.getContact();
        AirportEmployeeEmergencyContact emergencyContact = employee.getEmergencyContact();

        contact.setEmail(form.getContactEmail());
        contact.setPhone(form.getContactPhone());
        contact.setCity(form.getCity());
        contact.setAddress(form.getAddress());
        contact.setNotes(form.getNotes());

        emergencyContact.setName(form.getEmergencyContactName());
        emergencyContact.setRelation(form.getEmergencyContactRelation());
        emergencyContact.setPhone(form.getEmergencyContactPhone());

        employee.setFirstName(form.getFirstName());
        employee.setLastName(form.getLastName());
        employee.setRole(form.getRole());
        employee.setSex(form.getSex());
        employee.setBirthDate(form.getBirthDate());
        employee.setPassCountry(form.getCountry());
        employee.setPassNumber(form.getPassportNumber());
        employee.setContact(contact);
        employee.setEmergencyContact(emergencyContact);

        airportEmployeeContactRepository.save(contact);
        airportEmployeeEmergencyContactRepository.save(emergencyContact);
        airportEmployeeRepository.save(employee);
    }

    public AirportEmployeeForm getAirportEmployeeFormById(Integer id) {

        AirportEmployee employee = airportEmployeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airport employee not found"));

        AirportEmployeeContact contact = employee.getContact();
        AirportEmployeeEmergencyContact emergencyContact = employee.getEmergencyContact();

        AirportEmployeeForm form = new AirportEmployeeForm();

        form.setId(employee.getId());
        form.setFirstName(employee.getFirstName());
        form.setLastName(employee.getLastName());
        form.setRole(employee.getRole());
        form.setSex(employee.getSex());
        form.setBirthDate(employee.getBirthDate());
        form.setCountry(employee.getPassCountry());
        form.setPassportNumber(employee.getPassNumber());

        form.setContactEmail(contact.getEmail());
        form.setContactPhone(contact.getPhone());
        form.setCity(contact.getCity());
        form.setAddress(contact.getAddress());
        form.setNotes(contact.getNotes());

        form.setEmergencyContactName(emergencyContact.getName());
        form.setEmergencyContactRelation(emergencyContact.getRelation());
        form.setEmergencyContactPhone(emergencyContact.getPhone());

        return form;
    }

    @Transactional
    public void deleteAirportEmployee(Integer id) {

        AirportEmployee employee = airportEmployeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airport employee not found"));

        AirportEmployeeContact contact = employee.getContact();
        AirportEmployeeEmergencyContact emergencyContact = employee.getEmergencyContact();

        airportEmployeeRepository.delete(employee);
        airportEmployeeContactRepository.delete(contact);
        airportEmployeeEmergencyContactRepository.delete(emergencyContact);
    }

    public boolean existsByEmail(String email) {
        return airportEmployeeRepository.existsByContact_Email(email);
    }

    public boolean existsByEmailAndIdNot(String email, Integer id) {
        return airportEmployeeRepository.existsByContact_EmailAndIdNot(email, id);
    }
}
