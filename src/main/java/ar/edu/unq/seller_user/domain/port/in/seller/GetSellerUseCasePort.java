package ar.edu.unq.seller_user.domain.port.in.seller;

import ar.edu.unq.seller_user.domain.model.Seller;

import java.util.Optional;

public interface GetSellerUseCasePort {
    Optional<Seller> getSellerById(String sellerId);
}
