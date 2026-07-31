package airport.entity;

import jakarta.persistence.*;
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

    @Column(name = "role_name")
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<FlightCrewLink> crewLinks = new HashSet<>();
}
