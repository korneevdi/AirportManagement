package airport.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "flight_bag_claims_link")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightBaggageClaimLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id")
    private BaggageClaim claim;
}
