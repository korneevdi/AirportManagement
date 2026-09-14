package airport.service;

import airport.entity.PassControlType;
import airport.repository.PassControlTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassControlTypeService {

    private final PassControlTypeRepository passControlTypeRepository;

    public PassControlTypeService(PassControlTypeRepository repository) {
        this.passControlTypeRepository = repository;
    }

    public List<PassControlType> getAllPassControlTypes() {
        return passControlTypeRepository.findAll();
    }

    public PassControlType getPassControlTypeDyId(Integer id) {
        return passControlTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Passport control type not found"));
    }

    public PassControlType savePassControlType(PassControlType passControlType) {
        return passControlTypeRepository.save(passControlType);
    }

    public void deletePassControlType(Integer id) {
        passControlTypeRepository.deleteById(id);
    }
}
