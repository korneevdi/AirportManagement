package airport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "check_in_counters")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CheckInCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counter_id")
    private Integer id;

    @Column(name = "counter_number")
    private String number;
}
