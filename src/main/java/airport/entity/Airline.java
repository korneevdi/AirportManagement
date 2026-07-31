package airport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "airlines")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id")
    private Integer id;

    @Column(name = "iata")
    private String iata;

    @Column(name = "icao")
    private String icao;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "contact")
    private AirlineContact contact;

    @OneToMany(mappedBy = "airline")
    private Set<Flight> flights = new HashSet<>();
}
