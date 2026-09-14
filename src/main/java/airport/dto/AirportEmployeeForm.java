package airport.dto;

import airport.entity.AirportEmployeeRole;
import airport.entity.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AirportEmployeeForm {

    @NotBlank(message = "Airport employee first name is required")
    @Size(max = 50, message = "Airport employee first name must not contain more than 50 symbols")
    private String firstName;

    @NotBlank(message = "Airport employee last name is required")
    @Size(max = 50, message = "Airport employee last name must not contain more than 50 symbols")
    private String lastName;

    @NotNull(message = "Role is required")
    private AirportEmployeeRole role;

    @NotNull(message = "Sex is required")
    private Sex sex;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Airport employee country is required")
    @Size(max = 20, message = "Airport employee country must not contain more than 20 symbols")
    private String country;

    @NotBlank(message = "Airport employee passport number is required")
    @Size(max = 20, message = "Airport employee passport number must not contain more than 20 symbols")
    private String passportNumber;


    // Contact
    @NotBlank(message = "Airport employee contact email address is required")
    @Size(max = 100, message = "Airport employee contact email address must not contain more than 100 symbols")
    private String contactEmail;

    @NotBlank(message = "Airport employee contact phone number is required")
    @Size(max = 30, message = "Airport employee contact phone number must not contain more than 30 symbols")
    private String contactPhone;

    @NotBlank(message = "Airport employee city is required")
    @Size(max = 25, message = "Airport employee city must not contain more than 25 symbols")
    private String city;

    @NotBlank(message = "Airport employee address is required")
    @Size(max = 200, message = "Airport employee address must not contain more than 200 symbols")
    private String address;

    private String notes;


    // Emergency contact
    @NotBlank(message = "Airport employee emergency contact name is required")
    @Size(max = 100, message = "Airport employee emergency contact name must not contain more than 100 symbols")
    private String emergencyContactName;

    @Size(max = 30, message = "Airport employee emergency contact relation must not contain more than 30 symbols")
    private String emergencyContactRelation;

    @NotBlank(message = "Airport employee emergency contact phone number is required")
    @Size(max = 30, message = "Airport employee emergency contact phone number must not contain more than 30 symbols")
    private String emergencyContactPhone;
}
