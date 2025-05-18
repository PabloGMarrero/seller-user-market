package ar.edu.unq.seller_user.infrastructure.web.in.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SellerCreateDTO {

    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 50, message = "Company Name length must be between 2 and 50 characters.")
    @JsonProperty("company_name")
    private String companyName;

    @NotBlank(message = "Company Email is required")
    @Size(min = 5, max = 254, message = "Company Email length must be between 5 and 254 characters.")
    @Email(message = "Company email must be in a valid format.")
    @JsonProperty("company_email")
    private String companyEmail;
}
