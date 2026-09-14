package airport.service;

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

    public Type getTypeById(Integer id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type not found"));
    }

    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    public void deleteType(Integer id) {
        typeRepository.deleteById(id);
    }
}
