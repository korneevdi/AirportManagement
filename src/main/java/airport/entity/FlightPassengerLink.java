package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

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
    @NotNull(message = "Flight is required")
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    @NotNull(message = "Passenger is required")
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
    private OffsetDateTime boardingTime;

    @Column(name = "seat_number")
    @Pattern(regexp = "[1-9][0-9]?[A-E]", message = "Invalid seat number")
    private String seatNumber;
}
