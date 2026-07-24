package airport.Service;

import airport.entity.BaggageClaim;
import airport.repository.BaggageClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaggageClaimService {

    private final BaggageClaimRepository baggageClaimRepository;

    public BaggageClaimService(BaggageClaimRepository repository) {
        this.baggageClaimRepository = repository;
    }

    public List<BaggageClaim> getAllBaggageClimes() {
        return baggageClaimRepository.findAll();
    }
}
