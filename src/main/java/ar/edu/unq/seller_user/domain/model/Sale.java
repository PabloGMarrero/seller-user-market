package ar.edu.unq.seller_user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Sale {

    private String id;

    private String productId;

    private String userId;

    private Double saleValue;
}
