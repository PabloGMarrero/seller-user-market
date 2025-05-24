package ar.edu.unq.seller_user.application.user;

import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.domain.port.in.user.DeleteUserUseCasePort;
import ar.edu.unq.seller_user.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteUserUseCaseAdapter implements DeleteUserUseCasePort {

    private final UserRepositoryPort userRepositoryPort;

    public DeleteUserUseCaseAdapter(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void deleteUser(String userId) {
        Optional<User> userOptional = userRepositoryPort.findByIdAndDeletedFalse(userId);

        if(userOptional.isPresent()) {
            User userWithId = userOptional.get();
            userWithId.markAsDeleted();
            userRepositoryPort.save(userWithId);
        }
    }
}
