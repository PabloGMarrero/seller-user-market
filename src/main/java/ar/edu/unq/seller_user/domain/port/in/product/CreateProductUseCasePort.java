package ar.edu.unq.seller_user.domain.port.in.product;

import ar.edu.unq.seller_user.domain.model.Product;
import ar.edu.unq.seller_user.infrastructure.web.dto.product.ProductCreateDTO;

public interface CreateProductUseCasePort {
    Product createProduct(ProductCreateDTO productCreateDTO);
}
