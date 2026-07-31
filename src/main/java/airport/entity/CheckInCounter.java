package airport.entity;

import jakarta.persistence.*;
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

    @Column(name = "counter_number")
    private String number;

    @OneToMany(mappedBy = "counter")
    private Set<FlightCheckInLink> flightLinks = new HashSet<>();
}
