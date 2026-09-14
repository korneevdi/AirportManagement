package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "IATA code is required")
    @Pattern(regexp = "^$|[A-Z]{3}", message = "IATA code must contain exactly 3 uppercase letters")
    @Column(name = "iata")
    private String iata;

    @NotBlank(message = "ICAO code is required")
    @Pattern(regexp = "^$|[A-Z]{4}", message = "ICAO code must contain exactly 4 uppercase letters")
    @Column(name = "icao")
    private String icao;

    @NotBlank(message = "Airport name is required")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "City is required")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "Country is required")
    @Column(name = "country")
    private String country;

    @NotBlank(message = "Airport time zone is required")
    @Column(name = "timezone")
    private String timeZone;

    @OneToMany(mappedBy = "departureAirport")
    private Set<Flight> departures = new HashSet<>();

    @OneToMany(mappedBy = "arrivalAirport")
    private Set<Flight> arrivals = new HashSet<>();
}
