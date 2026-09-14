package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flight_crews")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightCrew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_crew_id")
    private Integer id;

    @Size(max = 20, message = "Pilot license number must not contain more than 20 symbols")
    @Column(name = "pilot_license_number")
    private String pilotLicenseNumber;

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

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotBlank(message = "Flight crew country is required")
    @Size(max = 20, message = "Flight crew country must not contain more than 20 symbols")
    @Column(name = "passport_country")
    private String passCountry;

    @NotBlank(message = "Flight crew passport number is required")
    @Size(max = 20, message = "Flight crew passport number must not contain more than 20 symbols")
    @Column(name = "passport_number")
    private String passNumber;

    @OneToMany(mappedBy = "crew")
    private Set<FlightCrewLink> flightLinks = new HashSet<>();
}
