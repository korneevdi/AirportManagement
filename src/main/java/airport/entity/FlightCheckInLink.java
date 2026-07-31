package airport.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "flight_check_in_link")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightCheckInLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counter_id")
    private CheckInCounter counter;
}
