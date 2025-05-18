package ar.edu.unq.seller_user.infrastructure.web.in.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {

    private String id;

    private String name;

    @JsonProperty("last_name")
    private String lastName;

    private String email;
}
