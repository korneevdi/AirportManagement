package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "baggage_claims")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaggageClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Integer id;

    @NotBlank(message = "Baggage claim number is required")
    @Pattern(
            regexp = "^$|[0-9]{2}",
            message = "Baggage claim number must contain 1 to 2 digits")
    @Column(name = "claim_number")
    private String number;

    @OneToMany(mappedBy = "claim")
    private Set<FlightBaggageClaimLink> flightLinks = new HashSet<>();
}
