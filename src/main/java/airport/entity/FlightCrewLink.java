package airport.entity;

import jakarta.persistence.*;
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
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private FlightCrew crew;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role")
    private CrewRole role;
}
