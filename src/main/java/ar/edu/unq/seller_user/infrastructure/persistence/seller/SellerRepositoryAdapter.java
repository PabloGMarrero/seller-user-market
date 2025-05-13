package ar.edu.unq.seller_user.infrastructure.persistence.seller;

import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.out.SellerRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SellerRepositoryAdapter implements SellerRepositoryPort {
    private final SellerMongoRepository sellerMongoRepository;

    public SellerRepositoryAdapter(SellerMongoRepository sellerMongoRepository) {
        this.sellerMongoRepository = sellerMongoRepository;
    }

    @Override
    public Seller save(Seller seller) {
        SellerDocument sellerDocument = new SellerDocument(
                seller.getId(),
                seller.getCompanyName(),
                seller.getCompanyEmail(),
                seller.getDeleted()
        );

        sellerMongoRepository.save(sellerDocument);
        return seller;
    }

    @Override
    public Optional<Seller> findById(String sellerId) {
        return sellerMongoRepository.findById(sellerId).map(this::generateSellerFrom);
    }

    @Override
    public Optional<Seller> findByIdAndDeletedFalse(String sellerId) {
        return sellerMongoRepository.findByIdAndDeletedFalse(sellerId).map(this::generateSellerFrom);
    }

    @Override
    public boolean existsByCompanyEmail(String companyEmail) {
        return sellerMongoRepository.existsByCompanyMail(companyEmail);
    }

    private Seller generateSellerFrom(SellerDocument sellerDocument) {
        return new Seller(
                sellerDocument.getId(),
                sellerDocument.getCompanyName(),
                sellerDocument.getCompanyMail(),
                sellerDocument.getDeleted()
        );
    }
}
