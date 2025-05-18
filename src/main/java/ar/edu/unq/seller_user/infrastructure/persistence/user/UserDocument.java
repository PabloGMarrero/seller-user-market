package ar.edu.unq.seller_user.infrastructure.persistence.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@Document("users")
public class UserDocument {

    @Id
    private String id;

    private String name;

    @Field("last_name")
    private String lastName;

    private String email;

    private Boolean deleted;
}
