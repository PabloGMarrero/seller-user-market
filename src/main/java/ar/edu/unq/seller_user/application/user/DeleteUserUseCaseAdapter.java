package ar.edu.unq.seller_user.application.user;

import ar.edu.unq.seller_user.application.exceptions.ElementNotFoundException;
import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.domain.port.in.user.DeleteUserUseCasePort;
import ar.edu.unq.seller_user.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserUseCaseAdapter implements DeleteUserUseCasePort {

    private final UserRepositoryPort userRepositoryPort;

    public DeleteUserUseCaseAdapter(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void deleteUser(String userId) {
        User userWithId = userRepositoryPort.findById(userId).orElseThrow(() -> new ElementNotFoundException("User", userId));

        if(!userWithId.getDeleted()){
            userWithId.markAsDeleted();
            userRepositoryPort.save(userWithId);
        }
    }
}
