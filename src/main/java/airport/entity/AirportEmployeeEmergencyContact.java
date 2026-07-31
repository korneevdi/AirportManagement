package airport.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "emergency_contacts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AirportEmployeeEmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Integer id;

    @Column(name = "contact_name")
    private String name;

    @Column(name = "contact_relation")
    private String relation;

    @Column(name = "contact_phone")
    private String phone;
}
