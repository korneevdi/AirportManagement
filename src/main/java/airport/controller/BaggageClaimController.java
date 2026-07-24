package airport.controller;

import airport.Service.BaggageClaimService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaggageClaimController {

    private final BaggageClaimService baggageClaimService;

    public BaggageClaimController(BaggageClaimService service) {
        this.baggageClaimService = service;
    }

    @GetMapping("/baggage-claims")
    public String getBaggageClaims(Model model) {
        model.addAttribute(
                "baggageClaims",
                baggageClaimService.getAllBaggageClimes()
        );
        return "baggage-claims";
    }
}
