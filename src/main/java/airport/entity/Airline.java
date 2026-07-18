package airport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airlines")
@NoArgsConstructor
@AllArgsConstructor
@Data
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
}
