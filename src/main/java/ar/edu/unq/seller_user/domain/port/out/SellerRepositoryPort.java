package ar.edu.unq.seller_user.domain.port.out;

import ar.edu.unq.seller_user.domain.model.Seller;

import java.util.Optional;

public interface SellerRepositoryPort {

    Seller save(Seller seller);

    Optional<Seller> findById(String sellerId);

    Optional<Seller> findByIdAndDeletedFalse(String sellerId);

    boolean existsByCompanyEmail(String companyEmail);
}
