package airport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "flight_crews")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightCrew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_crew_id")
    private Integer id;

    @Column(name = "pilot_license_number")
    private String pilotLicenseNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "sex")
    private Sex sex;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "passport_country")
    private String passCountry;

    @Column(name = "passport_number")
    private String passNumber;
}
