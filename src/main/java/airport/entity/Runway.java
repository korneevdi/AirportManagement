package airport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flight_runways")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Runway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "runway_id")
    private Integer id;

    @Column(name = "runway_number")
    private String number;
}
