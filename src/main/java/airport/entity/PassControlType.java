package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Passport control type is required")
    @Size(max = 40, message = "Passport control type name must not contain more than 40 symbols")
    @Column(name = "type_name")
    private String name;

    @OneToMany(mappedBy = "passControlType")
    private Set<Flight> flights = new HashSet<>();
}
