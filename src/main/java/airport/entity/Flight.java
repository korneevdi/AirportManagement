package airport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flights")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Integer id;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "service_date")
    private LocalDate serviceDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline", nullable = false)
    private Airline airline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport", nullable = false)
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport", nullable = false)
    private Airport arrivalAirport;

    @Column(name = "scheduled_departure_time")
    private OffsetDateTime scheduledDepartureTime;

    @Column(name = "actual_departure_time")
    private OffsetDateTime actualDepartureTime;

    @Column(name = "scheduled_arrival_time")
    private OffsetDateTime scheduledArrivalTime;

    @Column(name = "actual_arrival_time")
    private OffsetDateTime actualArrivalTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane", nullable = false)
    private Airplane airplane;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_dispatcher", nullable = false)
    private AirportEmployee responsibleDispatcher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type", nullable = false)
    private Type flightType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status")
    private FlightStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_control")
    private PassControlType passControlType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_gate")
    private Gate departureGate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_gate")
    private Gate arrivalGate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_terminal")
    private Terminal departureTerminal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_terminal")
    private Terminal arrivalTerminal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "runway")
    private Runway runway;

    @OneToMany(mappedBy = "flight",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<FlightCheckInLink> checkInLinks = new HashSet<>();

    @OneToMany(mappedBy = "flight",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<FlightBaggageClaimLink> baggageClaimLinks = new HashSet<>();

    @OneToMany(mappedBy = "flight",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<FlightCrewLink> crewLinks = new HashSet<>();

    @OneToMany(mappedBy = "flight",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<FlightPassengerLink> passengerLinks = new HashSet<>();
}
