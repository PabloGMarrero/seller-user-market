package ar.edu.unq.seller_user.domain.port.out;

import ar.edu.unq.seller_user.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findById(String userId);

    Optional<User> findByIdAndDeletedFalse(String sellerId);

    boolean existsByEmail(String email);
}
