package airport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "airport_employees")
@NoArgsConstructor
@AllArgsConstructor
@Data
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
}
