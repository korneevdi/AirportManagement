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
}
