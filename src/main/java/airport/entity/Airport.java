package airport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "airports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id")
    private Integer id;

    @Column(name = "iata")
    private String iata;

    @Column(name = "icao")
    private String icao;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "timezone")
    private String timeZone;

    @OneToMany(mappedBy = "departureAirport")
    private Set<Flight> departures = new HashSet<>();

    @OneToMany(mappedBy = "arrivalAirport")
    private Set<Flight> arrivals = new HashSet<>();
}
