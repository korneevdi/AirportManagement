package airport.entity;

import jakarta.persistence.*;
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

    @Column(name = "gate_number")
    private String number;

    @OneToMany(mappedBy = "departureGate")
    private Set<Flight> departureFlights;

    @OneToMany(mappedBy = "arrivalGate")
    private Set<Flight> arrivalFlights;
}
