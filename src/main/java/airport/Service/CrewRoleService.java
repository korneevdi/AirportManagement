package airport.Service;

import airport.entity.CrewRole;
import airport.repository.CrewRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewRoleService {

    private final CrewRoleRepository crewRoleRepository;

    public CrewRoleService(CrewRoleRepository repository) {
        this.crewRoleRepository = repository;
    }

    public List<CrewRole> getAllCrewRoles() {
        return crewRoleRepository.findAll();
    }
}
