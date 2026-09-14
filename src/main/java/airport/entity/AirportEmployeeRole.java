package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Airport employee role name is required")
    @Size(max = 100, message = "Airport employee role name must not contain more than 100 symbols")
    @Column(name = "role_name")
    private String name;
}
