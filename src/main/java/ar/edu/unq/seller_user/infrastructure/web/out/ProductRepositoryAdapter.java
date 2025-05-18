package ar.edu.unq.seller_user.infrastructure.web.out;

import ar.edu.unq.seller_user.infrastructure.web.out.dto.ProductDTO;
import ar.edu.unq.seller_user.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {
    /*@Override
    public Optional<ProductDTO> findById(String productId) {
        return Optional.empty();
    }*/

    @Override
    public Optional<ProductDTO> findByIdAndDeletedFalse(String productId) {
        return Optional.empty();
    }

   /* @Override
    public boolean existsByNameAndSellerId(String productName, String sellerId) {
        return false;
    }

    @Override
    public List<ProductDTO> searchProductByName(String productName) {
        return List.of();
    }

    @Override
    public List<ProductDTO> searchProductByCategory(String productCategory) {
        return List.of();
    }

    @Override
    public List<ProductDTO> searchProductsWithBetweenParam(Double minPrice, Double maxPrice) {
        return List.of();
    }

    @Override
    public List<ProductDTO> searchProductsWithLessThanParam(Double price) {
        return List.of();
    }

    @Override
    public List<ProductDTO> searchProductsWithGreaterThanParam(Double price) {
        return List.of();
    }*/
}
