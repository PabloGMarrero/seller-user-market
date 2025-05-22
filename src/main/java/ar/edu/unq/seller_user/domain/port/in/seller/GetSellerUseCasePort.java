package ar.edu.unq.seller_user.domain.port.in.seller;

import ar.edu.unq.seller_user.domain.model.Seller;

public interface GetSellerUseCasePort {
    Seller getSellerById(String sellerId);
}
