package airport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "control_types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PassControlType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer id;

    @Column(name = "type_name")
    private String name;

    @OneToMany(mappedBy = "passControlType")
    private Set<Flight> flights = new HashSet<>();
}
