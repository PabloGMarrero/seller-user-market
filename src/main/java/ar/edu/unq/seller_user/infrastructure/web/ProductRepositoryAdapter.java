package ar.edu.unq.seller_user.infrastructure.web;

import ar.edu.unq.seller_user.domain.model.Product;
import ar.edu.unq.seller_user.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {
    @Override
    public Optional<Product> findById(String productId) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> findByIdAndDeletedFalse(String productId) {
        return Optional.empty();
    }

    @Override
    public boolean existsByNameAndSellerId(String productName, String sellerId) {
        return false;
    }

    @Override
    public List<Product> searchProductByName(String productName) {
        return List.of();
    }

    @Override
    public List<Product> searchProductByCategory(String productCategory) {
        return List.of();
    }

    @Override
    public List<Product> searchProductsWithBetweenParam(Double minPrice, Double maxPrice) {
        return List.of();
    }

    @Override
    public List<Product> searchProductsWithLessThanParam(Double price) {
        return List.of();
    }

    @Override
    public List<Product> searchProductsWithGreaterThanParam(Double price) {
        return List.of();
    }
}
