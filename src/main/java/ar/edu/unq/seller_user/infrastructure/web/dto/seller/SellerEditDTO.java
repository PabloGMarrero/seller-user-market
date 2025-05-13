package ar.edu.unq.seller_user.infrastructure.web.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SellerEditDTO {

    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 50, message = "Company name length must be between 2 and 50 characters.")
    @JsonProperty("company_name")
    private String companyName;
}
