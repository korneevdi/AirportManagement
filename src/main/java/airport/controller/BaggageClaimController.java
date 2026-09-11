package airport.controller;

import airport.service.BaggageClaimService;
import airport.entity.BaggageClaim;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/baggage-claims/new")
    public String showBaggageClaimForm(Model model) {
        model.addAttribute("baggageClaim", new BaggageClaim());
        return "baggage-claim-form";
    }

    @PostMapping("/baggage-claims")
    public String createBaggageClaim(
            @Valid @ModelAttribute("baggageClaim") BaggageClaim claim,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "baggage-claim-form";
        }
        baggageClaimService.saveBaggageClaim(claim);
        return "redirect:baggage-claims";
    }
}
