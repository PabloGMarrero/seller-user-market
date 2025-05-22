package ar.edu.unq.seller_user.infrastructure.web.in.rest.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserEditDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name length must be between 2 and 50 characters.")
    private String name;

    @NotBlank(message = "Lastname is required")
    @Size(min = 2, max = 50, message = "LastName length must be between 2 and 50 characters.")
    @JsonProperty("last_name")
    private String lastName;
}
