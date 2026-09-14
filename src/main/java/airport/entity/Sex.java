package airport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Sex name is required")
    @Size(max = 20, message = "Sex name must not contain more than 20 symbols")
    @Column(name = "sex_name")
    private String name;
}
