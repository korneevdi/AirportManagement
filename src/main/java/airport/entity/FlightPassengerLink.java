package airport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight_passenger_link")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightPassengerLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @Column(name = "checked_in")
    private Boolean isCheckedIn;

    @Column(name = "passed_control")
    private Boolean isPassedControl;

    @Column(name = "boarded")
    private Boolean isBoarded;

    @Column(name = "boarding_group")
    private Integer boardingGroup;

    @Column(name = "boarding_time")
    private LocalDateTime boardingTime;

    @Column(name = "seat_number")
    private String seatNumber;
}
