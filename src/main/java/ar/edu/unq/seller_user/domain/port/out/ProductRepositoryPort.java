package ar.edu.unq.seller_user.domain.port.out;

import ar.edu.unq.seller_user.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    //TODO ver que hacemos porque acá no tenemos el Producto, dto?
    Optional<Product> findById(String productId);

    Optional<Product> findByIdAndDeletedFalse(String productId);

    boolean existsByNameAndSellerId(String productName, String sellerId);

    List<Product> searchProductByName(String productName);

    List<Product> searchProductByCategory(String productCategory);

    List<Product> searchProductsWithBetweenParam(Double minPrice, Double maxPrice);

    List<Product> searchProductsWithLessThanParam(Double price);

    List<Product> searchProductsWithGreaterThanParam(Double price);
}
