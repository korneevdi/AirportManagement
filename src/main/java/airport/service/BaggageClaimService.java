package airport.service;

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

    public BaggageClaim getBaggageClaimById(Integer id) {
        return baggageClaimRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Baggage claim not found"));
    }

    public BaggageClaim saveBaggageClaim(BaggageClaim claim) {
        return baggageClaimRepository.save(claim);
    }

    public void deleteBaggageClaim(Integer id) {
        baggageClaimRepository.deleteById(id);
    }
}
