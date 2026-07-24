package airport.Service;

import airport.entity.Sex;
import airport.repository.SexRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SexService {

    private final SexRepository sexRepository;

    public SexService(SexRepository repository) {
        this.sexRepository = repository;
    }

    public List<Sex> getAllSexes() {
        return sexRepository.findAll();
    }
}
