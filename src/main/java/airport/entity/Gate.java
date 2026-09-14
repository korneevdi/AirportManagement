package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "gates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gate_id")
    private Integer id;

    @NotBlank(message = "Gate number is required")
    @Pattern(
            regexp = "^$|[A-Z][0-9]{1,2}",
            message = "Gate number must contain one uppercase letter followed by 1 to 2 digits")
    @Column(name = "gate_number")
    private String number;

    @OneToMany(mappedBy = "departureGate")
    private Set<Flight> departureFlights;

    @OneToMany(mappedBy = "arrivalGate")
    private Set<Flight> arrivalFlights;
}
