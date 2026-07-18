package airport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airline_contacts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AirlineContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Integer id;

    @Column(name = "contact_name")
    private String name;

    @Column(name = "contact_email")
    private String email;

    @Column(name = "contact_phone")
    private String phone;

    @Column(name = "city")
    private String city;

    @Column(name = "notes")
    private String notes;
}
