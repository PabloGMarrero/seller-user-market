package ar.edu.unq.seller_user.infrastructure.persistence.seller;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerMongoRepository extends MongoRepository<SellerDocument, String> {

    Optional<SellerDocument> findByIdAndDeletedFalse(String sellerId);
    boolean existsByCompanyMail(String companyMail);
}
