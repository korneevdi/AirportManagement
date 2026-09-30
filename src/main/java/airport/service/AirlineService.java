package airport.service;

import airport.dto.AirlineForm;
import airport.entity.Airline;
import airport.entity.AirlineContact;
import airport.repository.AirlineContactRepository;
import airport.repository.AirlineRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {

    private final AirlineRepository airlineRepository;
    private final AirlineContactRepository airlineContactRepository;

    public AirlineService(
            AirlineRepository airlineRepository,
            AirlineContactRepository airlineContactRepository) {
        this.airlineRepository = airlineRepository;
        this.airlineContactRepository = airlineContactRepository;
    }

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public Airline getAirlineById(Integer id) {
        return airlineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airline not found"));
    }

    @Transactional
    public void saveAirline(AirlineForm form) {

        AirlineContact contact = new AirlineContact();
        contact.setName(form.getContactName());
        contact.setEmail(form.getContactEmail());
        contact.setPhone(form.getContactPhone());
        contact.setCity(form.getCity());
        contact.setNotes(form.getNotes());
        airlineContactRepository.save(contact);

        Airline airline = new Airline();
        airline.setIata(form.getIata());
        airline.setIcao(form.getIcao());
        airline.setName(form.getName());
        airline.setContact(contact);
        airlineRepository.save(airline);
    }

    @Transactional
    public void updateAirline(Integer id, AirlineForm form) {

        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airline not found"));

        AirlineContact contact = airline.getContact();

        contact.setName(form.getContactName());
        contact.setEmail(form.getContactEmail());
        contact.setPhone(form.getContactPhone());
        contact.setCity(form.getCity());
        contact.setNotes(form.getNotes());

        airline.setIata(form.getIata());
        airline.setIcao(form.getIcao());
        airline.setName(form.getName());

        airlineContactRepository.save(contact);
        airlineRepository.save(airline);
    }

    public AirlineForm getAirlineFormById(Integer id) {

        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airline not found"));

        AirlineContact contact = airline.getContact();

        AirlineForm form = new AirlineForm();

        form.setId(airline.getId());
        form.setIata(airline.getIata());
        form.setIcao(airline.getIcao());
        form.setName(airline.getName());

        form.setContactName(contact.getName());
        form.setContactEmail(contact.getEmail());
        form.setContactPhone(contact.getPhone());
        form.setCity(contact.getCity());
        form.setNotes(contact.getNotes());

        return form;
    }

    @Transactional
    public void deleteAirline(Integer id) {

        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airline not found"));

        AirlineContact contact = airline.getContact();

        airlineRepository.delete(airline);
        airlineContactRepository.delete(contact);
    }

    public boolean existsByIata(String iata) {
        return airlineRepository.existsByIata(iata);
    }

    public boolean existsByIataAndIdNot(String iata, Integer id) {
        return airlineRepository.existsByIataAndIdNot(iata, id);
    }

    public boolean existsByIcao(String icao) {
        return airlineRepository.existsByIcao(icao);
    }

    public boolean existsByIcaoAndIdNot(String icao, Integer id) {
        return airlineRepository.existsByIcaoAndIdNot(icao, id);
    }

    public boolean existsByName(String name) {
        return airlineRepository.existsByName(name);
    }

    public boolean existsByNameAndIdNot(String name, Integer id) {
        return airlineRepository.existsByNameAndIdNot(name, id);
    }

    public boolean existsByEmail(String email) {
        return airlineContactRepository.existsByEmail(email);
    }

    public boolean existsByEmailAndIdNot(String email, Integer id) {
        return airlineContactRepository.existsByEmailAndIdNot(email, id);
    }
}
