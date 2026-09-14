package airport.service;

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

    public CrewRole getCrewRoleById(Integer id) {
        return crewRoleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Crew role not found"));
    }

    public CrewRole saveCrewRole(CrewRole crewRole) {
        return crewRoleRepository.save(crewRole);
    }

    public void deleteCrewRole(Integer id) {
        crewRoleRepository.deleteById(id);
    }
}
