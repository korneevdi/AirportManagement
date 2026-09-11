package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer id;

    @NotBlank(message = "Type name is required")
    @Size(max = 30, message = "Type name must not contain more than 30 symbols")
    @Column(name = "type_name")
    private String name;

    @OneToMany(mappedBy = "flightType")
    Set<Flight> flights = new HashSet<>();
}
