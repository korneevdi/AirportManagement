package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Flight is required")
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counter_id")
    @NotNull(message = "Check-in counter is required")
    private CheckInCounter counter;
}
