package ar.edu.unq.seller_user.domain.port.in.product;

import ar.edu.unq.seller_user.domain.model.Product;
import ar.edu.unq.seller_user.infrastructure.web.dto.product.ProductEditDTO;

public interface EditProductUseCasePort {
    Product editProduct(String productId, ProductEditDTO productEditDTO);
}
