package airport.Service;

import airport.entity.Type;
import airport.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository repository) {
        this.typeRepository = repository;
    }

    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }
}
