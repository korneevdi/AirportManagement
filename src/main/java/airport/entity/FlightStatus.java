package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "statuses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer id;

    @NotBlank(message = "Status name is required")
    @Size(max = 40, message = "Flight status name must not contain more than 40 symbols")
    @Column(name = "status_name")
    private String name;

    @OneToMany(mappedBy = "status")
    private Set<Flight> flights = new HashSet<>();
}
