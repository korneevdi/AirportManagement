package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Terminal name is required")
    @Pattern(
            regexp = "^$|[A-Z]{1}",
            message = "Terminal name must contain exactly one uppercase letter")
    @Column(name = "terminal_number")
    private String number;

    @OneToMany(mappedBy = "departureTerminal")
    private Set<Flight> departureFlights;

    @OneToMany(mappedBy = "arrivalTerminal")
    private Set<Flight> arrivalFlights;
}
