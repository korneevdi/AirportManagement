package airport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airplanes")
@NoArgsConstructor
@AllArgsConstructor
@Data
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
}
