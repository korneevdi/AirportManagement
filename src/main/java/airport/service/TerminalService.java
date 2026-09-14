package airport.service;

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

    public Terminal getTerminalById(Integer id) {
        return terminalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Terminal not found"));
    }

    public Terminal saveTerminal(Terminal terminal) {
        return terminalRepository.save(terminal);
    }

    public void deleteTerminal(Integer id) {
        terminalRepository.deleteById(id);
    }
}
