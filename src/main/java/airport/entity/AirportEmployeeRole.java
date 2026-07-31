package airport.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airport_employee_roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AirportEmployeeRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role_name")
    private String name;
}
