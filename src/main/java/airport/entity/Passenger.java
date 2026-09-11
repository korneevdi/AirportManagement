package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "passengers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not contain more than 50 symbols")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not contain more than 50 symbols")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Sex is required")
    @ManyToOne
    @JoinColumn(name = "sex")
    private Sex sex;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age must be positive")
    @Max(value = 130, message = "Age is unlikely to exceed 130 years")
    @Column(name = "age")
    private Integer age;

    @NotBlank(message = "Passenger home country is required")
    @Size(max = 20, message = "Passenger home country must not contain more than 20 symbols")
    @Column(name = "passport_country")
    private String passCountry;

    @NotBlank(message = "Passenger passport number is required")
    @Size(max = 20, message = "Passenger passport number must not contain more than 20 symbols")
    @Column(name = "passport_number")
    private String passNumber;

    @OneToMany(mappedBy = "passenger")
    private Set<FlightPassengerLink> flightLinks = new HashSet<>();
}
