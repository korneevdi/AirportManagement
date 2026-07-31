package airport.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sexes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sex_id")
    private Integer id;

    @Column(name = "sex_name")
    private String name;
}
