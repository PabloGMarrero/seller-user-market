package ar.edu.unq.seller_user.domain.port.out;

import ar.edu.unq.seller_user.infrastructure.web.out.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    //Optional<ProductDTO> findById(String productId);

    Optional<ProductDTO> findByIdAndDeletedFalse(String productId);

    /*boolean existsByNameAndSellerId(String productName, String sellerId);

    List<ProductDTO> searchProductByName(String productName);

    List<ProductDTO> searchProductByCategory(String productCategory);

    List<ProductDTO> searchProductsWithBetweenParam(Double minPrice, Double maxPrice);

    List<ProductDTO> searchProductsWithLessThanParam(Double price);

    List<ProductDTO> searchProductsWithGreaterThanParam(Double price);*/
}
