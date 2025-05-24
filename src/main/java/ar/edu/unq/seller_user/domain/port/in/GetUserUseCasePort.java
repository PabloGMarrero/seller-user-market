package ar.edu.unq.seller_user.domain.port.in;

import ar.edu.unq.seller_user.domain.model.User;

import java.util.Optional;

public interface GetUserUseCasePort {
    Optional<User> getUserById(String id);
}
