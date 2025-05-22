package ar.edu.unq.seller_user.infrastructure.persistence.user;

import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserMongoRepository userMongoRepository;

    public UserRepositoryAdapter(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public User save(User user) {
        UserDocument userDocument = new UserDocument(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getDeleted()
        );

        userMongoRepository.save(userDocument);
        return user;
    }

    @Override
    public Optional<User> findByIdAndDeletedFalse(String sellerId) {
        return userMongoRepository.findByIdAndDeletedFalse(sellerId).map(this::generateUserFrom);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userMongoRepository.existsByEmail(email);
    }

    private User generateUserFrom(UserDocument userDocument) {
        return new User(
                userDocument.getId(),
                userDocument.getName(),
                userDocument.getLastName(),
                userDocument.getEmail(),
                userDocument.getDeleted()
        );
    }
}
