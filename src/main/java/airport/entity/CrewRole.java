package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "crew_roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CrewRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @NotBlank(message = "Crew role is required")
    @Size(max = 50, message = "Crew role name must not contain more than 50 symbols")
    @Column(name = "role_name")
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<FlightCrewLink> crewLinks = new HashSet<>();
}
