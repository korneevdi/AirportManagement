package airport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sexes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sex_id")
    private Integer id;

    @Column(name = "sex_name")
    private String name;
}
