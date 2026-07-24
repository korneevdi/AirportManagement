package airport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "baggage_claims")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaggageClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Integer id;

    @Column(name = "claim_number")
    private String number;
}
