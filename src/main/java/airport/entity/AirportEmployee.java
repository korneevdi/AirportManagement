package airport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "airport_employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AirportEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_employee_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "role")
    private AirportEmployeeRole role;

    @ManyToOne
    @JoinColumn(name = "sex")
    private Sex sex;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "passport_country")
    private String passCountry;

    @Column(name = "passport_number")
    private String passNumber;

    @OneToOne
    @JoinColumn(name = "contact")
    private AirportEmployeeContact contact;

    @OneToOne
    @JoinColumn(name = "emergency_contact")
    private AirportEmployeeEmergencyContact emergencyContact;

    @OneToMany(mappedBy = "responsibleDispatcher")
    private Set<Flight> flights = new HashSet<>();
}
