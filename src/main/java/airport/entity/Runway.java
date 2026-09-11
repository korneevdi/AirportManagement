package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flight_runways")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Runway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "runway_id")
    private Integer id;

    @NotBlank(message = "Runway number is required")
    @Pattern(
            regexp = "^$|(0[1-9]|[12][0-9]|3[0-6])[LR]",
            message = "Runway number must be from 01 to 36 followed by L or R")
    @Column(name = "runway_number")
    private String number;

    @OneToMany(mappedBy = "runway")
    private Set<Flight> flights = new HashSet<>();
}
