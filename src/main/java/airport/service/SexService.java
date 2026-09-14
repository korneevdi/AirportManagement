package airport.service;

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

    public Sex getSexById(Integer id) {
        return sexRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sex not found"));
    }

    public Sex saveSex(Sex sex) {
        return sexRepository.save(sex);
    }

    public void deleteSex(Integer id) {
        sexRepository.deleteById(id);
    }
}
