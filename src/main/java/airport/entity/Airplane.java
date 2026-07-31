package airport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "airplanes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airplane_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "airline")
    private Airline airline;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "total_capacity")
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "type")
    private Type type;

    @OneToMany(mappedBy = "airplane")
    private Set<Flight> flights = new HashSet<>();
}
