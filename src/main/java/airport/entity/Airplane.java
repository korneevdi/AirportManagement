package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Airline is required")
    @ManyToOne
    @JoinColumn(name = "airline")
    private Airline airline;

    @NotBlank(message = "Airplane registration number is required")
    @Size(max = 20, message = "Airplane registration number must not contain more than 20 symbols")
    @Column(name = "registration_number")
    private String registrationNumber;

    @NotBlank(message = "Airplane model is required")
    @Size(max = 30, message = "Airplane model must not contain more than 30 symbols")
    @Column(name = "model")
    private String model;

    @NotNull(message = "Capacity is required")
    @PositiveOrZero(message = "Capacity must not be negative")
    @Column(name = "total_capacity")
    private Integer capacity;

    @NotNull(message = "Airplane type is required")
    @ManyToOne
    @JoinColumn(name = "type")
    private Type type;

    @OneToMany(mappedBy = "airplane")
    private Set<Flight> flights = new HashSet<>();
}
