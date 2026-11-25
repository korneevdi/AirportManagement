package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "flight_crew_link")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightCrewLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    @NotNull(message = "Flight is required")
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    @NotNull(message = "Flight crew is required")
    private FlightCrew crew;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role")
    @NotNull(message = "Role is required")
    private CrewRole role;
}
