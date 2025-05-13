package ar.edu.unq.seller_user.infrastructure.persistence.seller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@Document("sellers")
public class SellerDocument {

    @Id
    private String id;

    @Field("company_name")
    private String companyName;

    @Field("company_mail")
    private String companyMail;

    private Boolean deleted;
}
