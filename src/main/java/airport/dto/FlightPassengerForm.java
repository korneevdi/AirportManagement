package airport.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightPassengerForm {

    @Min(value = 1, message = "Boarding group must be a positive number")
    private Integer boardingGroup;

    @Pattern(
            regexp = "^$|[1-9][0-9]?[A-E]",
            message = "Seat number must contain one or two digits followed by one uppercase letter")
    private String seatNumber;

    private Boolean isCheckedIn;

    private Boolean isPassedControl;

    private Boolean isBoarded;

    private LocalDateTime boardingTime;
}
