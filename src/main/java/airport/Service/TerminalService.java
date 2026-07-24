package airport.Service;

import airport.entity.Terminal;
import airport.repository.TerminalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {

    private final TerminalRepository terminalRepository;

    public TerminalService(TerminalRepository repository) {
        this.terminalRepository = repository;
    }

    public List<Terminal> getAllTerminals() {
        return terminalRepository.findAll();
    }
}
