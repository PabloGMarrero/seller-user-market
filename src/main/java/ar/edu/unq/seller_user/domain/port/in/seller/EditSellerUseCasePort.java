package ar.edu.unq.seller_user.domain.port.in.seller;

import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.infrastructure.web.in.dto.seller.SellerEditDTO;

public interface EditSellerUseCasePort {
    Seller editSeller(String sellerId, SellerEditDTO sellerEditDTO);
}
