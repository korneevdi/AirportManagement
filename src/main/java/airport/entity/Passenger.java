package airport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "passengers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "sex")
    private Sex sex;

    @Column(name = "age")
    private Integer age;

    @Column(name = "passport_country")
    private String passCountry;

    @Column(name = "passport_number")
    private String passNumber;
}
