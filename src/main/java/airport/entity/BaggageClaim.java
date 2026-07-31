package airport.entity;

import jakarta.persistence.*;
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

    @Column(name = "claim_number")
    private String number;

    @OneToMany(mappedBy = "claim")
    private Set<FlightBaggageClaimLink> flightLinks = new HashSet<>();
}
