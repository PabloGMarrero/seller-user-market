package ar.edu.unq.seller_user.application.user;

import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.domain.port.in.GetUserUseCasePort;
import ar.edu.unq.seller_user.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserUseCaseAdapter implements GetUserUseCasePort {

    private final UserRepositoryPort userRepositoryPort;

    public GetUserUseCaseAdapter(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Optional<User> getSellerById(String sellerId) {
        return userRepositoryPort.findByIdAndDeletedFalse(sellerId);
    }
}
