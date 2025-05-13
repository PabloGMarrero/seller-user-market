package ar.edu.unq.seller_user.domain.port.in.product;

import ar.edu.unq.seller_user.domain.model.Product;
import ar.edu.unq.seller_user.infrastructure.web.dto.product.ProductSearchFilterDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface SearchProductUserCasePort {
    List<Product> searchProductByName(String productName);
    List<Product> searchProductByCategory(String productCategory);
    List<Product> searchProductByFilter(@Valid ProductSearchFilterDTO productSearchFilterDTO);
}
