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
        return typeRepository.findAllByOrderByNameAsc();
    }

    public Type getTypeById(Integer id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type not found"));
    }

    public boolean existsByName(String name) {
        return typeRepository.existsByName(name);
    }

    public boolean existsByNameAndIdNot(String name, Integer id) {
        return typeRepository.existsByNameAndIdNot(name, id);
    }

    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    public void deleteType(Integer id) {
        typeRepository.deleteById(id);
    }
}
