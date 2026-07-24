package airport.Service;

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
}
