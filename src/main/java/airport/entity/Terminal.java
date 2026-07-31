package airport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "terminals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terminal_id")
    private Integer id;

    @Column(name = "terminal_number")
    private String number;

    @OneToMany(mappedBy = "departureTerminal")
    private Set<Flight> departureFlights;

    @OneToMany(mappedBy = "arrivalTerminal")
    private Set<Flight> arrivalFlights;
}
