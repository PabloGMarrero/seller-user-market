package ar.edu.unq.seller_user.domain.port.in.seller;

import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.infrastructure.web.dto.seller.SellerCreateDTO;

public interface CreateSellerUseCasePort {
    Seller createSeller(SellerCreateDTO sellerCreateDTO);
}
