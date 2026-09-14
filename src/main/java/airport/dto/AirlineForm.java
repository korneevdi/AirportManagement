package airport.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirlineForm {

    @NotBlank(message = "IATA is required")
    @Pattern(
            regexp = "^$|[A-Z]{2}",
            message = "IATA number must contain exactly 2 uppercase letters")
    private String iata;

    @NotBlank(message = "ICAO is required")
    @Pattern(
            regexp = "^$|[A-Z]{3}",
            message = "ICAO number must contain exactly 3 uppercase letters")
    private String icao;

    @NotBlank(message = "Airline name is required")
    @Size(max = 60, message = "Airline name must not contain more than 60 symbols")
    private String name;

    @NotBlank(message = "Airline contact name is required")
    @Size(max = 100, message = "Airline contact name must not contain more than 100 symbols")
    private String contactName;

    @NotBlank(message = "Airline contact email address is required")
    @Size(max = 100, message = "Airline contact email address must not contain more than 100 symbols")
    private String contactEmail;

    @NotBlank(message = "Airline contact phone number is required")
    @Size(max = 30, message = "Airline contact phone number must not contain more than 30 symbols")
    private String contactPhone;

    @NotBlank(message = "Airline home city is required")
    @Size(max = 25, message = "Airline home city must not contain more than 25 symbols")
    private String city;

    private String notes;
}
