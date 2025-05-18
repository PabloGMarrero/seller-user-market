package ar.edu.unq.seller_user.infrastructure.persistence.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMongoRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByIdAndDeletedFalse(String username);
    boolean existsByEmail(String email);
}
