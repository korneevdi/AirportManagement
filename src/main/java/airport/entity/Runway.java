package airport.entity;

import jakarta.persistence.*;
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

    @Column(name = "runway_number")
    private String number;

    @OneToMany(mappedBy = "runway")
    private Set<Flight> flights = new HashSet<>();
}
