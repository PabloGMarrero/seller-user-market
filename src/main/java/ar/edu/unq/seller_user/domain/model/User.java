package ar.edu.unq.seller_user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private Boolean deleted;

    public User(String id, String name, String lastName, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.deleted = false;
    }

    public void markAsDeleted() {
        this.deleted = true;
    }
}
