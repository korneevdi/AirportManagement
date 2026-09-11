package airport.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerForm {

    @Size(max = 50, message = "Customer first name must not contain more than 50 symbols")
    @NotBlank(message = "Customer first name is required")
    private String firstName;

    @Size(max = 50, message = "Customer last name must not contain more than 50 symbols")
    @NotBlank(message = "Customer last name is required")
    private String lastName;

    @Size(max = 20, message = "Customer country must not contain more than 20 symbols")
    @NotBlank(message = "Customer country is required")
    private String country;

    @Size(max = 20, message = "Customer passport number must not contain more than 20 symbols")
    @NotBlank(message = "Customer passport number is required")
    private String passportNumber;

    @Size(max = 100, message = "Customer contact email address must not contain more than 100 symbols")
    @NotBlank(message = "Customer contact email is required")
    private String contactEmail;

    @Size(max = 200, message = "Customer contact phone must not contain more than 200 symbols")
    @NotBlank(message = "Customer contact phone is required")
    private String contactPhone;

    @Size(max = 25, message = "Customer city must not contain more than 25 symbols")
    private String city;

    @Size(max = 200, message = "Customer address must not contain more than 200 symbols")
    private String address;

    private String notes;
}
