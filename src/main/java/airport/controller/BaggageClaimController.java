package airport.controller;

import airport.service.BaggageClaimService;
import airport.entity.BaggageClaim;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/baggage-claims/{id}/edit")
    public String showEditBaggageClaimForm(@PathVariable Integer id, Model model) {
        model.addAttribute("baggageClaim", baggageClaimService.getBaggageClaimById(id));
        return "baggage-claim-form";
    }

    @PostMapping("/baggage-claims/{id}")
    public String updateBaggageClaim(
            @PathVariable Integer id,
            @Valid @ModelAttribute("baggageClaim") BaggageClaim claim,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "baggage-claim-form";
        }

        claim.setId(id);
        baggageClaimService.saveBaggageClaim(claim);
        return "redirect:/baggage-claims";
    }

    @PostMapping("/baggage-claims/{id}/delete")
    public String deleteBaggageClaim(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try{
            baggageClaimService.deleteBaggageClaim(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete baggage claim because it is used by one or more flights."
            );
            redirectAttributes.addFlashAttribute("errorBaggageClaimId", id);
        }
        return "redirect:/baggage-claims";
    }
}
