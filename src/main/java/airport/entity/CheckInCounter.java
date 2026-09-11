package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "check_in_counters")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CheckInCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counter_id")
    private Integer id;

    @NotBlank(message = "Check-in counter number is required")
    @Pattern(
            regexp = "^$|[A-Z][0-9]{1,3}",
            message = "Check-in counter number must contain one uppercase letter followed by 1 to 3 digits")
    @Column(name = "counter_number")
    private String number;

    @OneToMany(mappedBy = "counter")
    private Set<FlightCheckInLink> flightLinks = new HashSet<>();
}
