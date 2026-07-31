package airport.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airport_employee_contacts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AirportEmployeeContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Integer id;

    @Column(name = "contact_email")
    private String email;

    @Column(name = "contact_phone")
    private String phone;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "notes")
    private String notes;
}
