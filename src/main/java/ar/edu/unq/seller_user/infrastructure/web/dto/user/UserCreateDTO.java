package ar.edu.unq.seller_user.infrastructure.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name length must be between 2 and 50 characters.")
    private String name;

    @NotBlank(message = "Lastname is required")
    @Size(min = 2, max = 50, message = "LastName length must be between 2 and 50 characters.")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Size(min = 5, max = 254, message = "Email length must be between 5 and 254 characters.")
    @Email(message = "Email must be in a valid format.")
    private String email;
}
