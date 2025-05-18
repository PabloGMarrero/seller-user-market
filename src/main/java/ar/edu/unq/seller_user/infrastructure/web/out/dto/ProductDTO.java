package ar.edu.unq.seller_user.infrastructure.web.out.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private String id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Integer stock;
    private String sellerId;
    private Boolean deleted;

    public ProductDTO(String id, String name, String description, String category, Double price, Integer stock, String sellerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.sellerId = sellerId;
        this.deleted = false;
    }
}
