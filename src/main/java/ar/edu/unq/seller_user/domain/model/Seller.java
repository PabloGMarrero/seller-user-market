package ar.edu.unq.seller_user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Seller {

    private String id;
    private String companyName;
    private String companyEmail;
    private Boolean deleted;

    public Seller(String id, String companyName, String companyEmail) {
        this.id = id;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.deleted = false;
    }

    public void markAsDeleted() {
        this.deleted = true;
    }
}
