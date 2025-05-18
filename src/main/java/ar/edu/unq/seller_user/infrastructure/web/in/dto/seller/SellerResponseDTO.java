package ar.edu.unq.seller_user.infrastructure.web.in.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellerResponseDTO {

    private String id;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("company_email")
    private String companyEmail;
}
