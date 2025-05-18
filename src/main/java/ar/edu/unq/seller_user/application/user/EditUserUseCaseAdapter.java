package ar.edu.unq.seller_user.application.user;

import ar.edu.unq.seller_user.application.exceptions.ElementNotFoundException;
import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.domain.port.in.user.EditUserUseCasePort;
import ar.edu.unq.seller_user.domain.port.out.UserRepositoryPort;
import ar.edu.unq.seller_user.infrastructure.web.in.dto.user.UserEditDTO;
import org.springframework.stereotype.Service;

@Service
public class EditUserUseCaseAdapter implements EditUserUseCasePort {

    private final UserRepositoryPort userRepositoryPort;

    public EditUserUseCaseAdapter(UserRepositoryPort userRepository) {
        this.userRepositoryPort = userRepository;
    }

    @Override
    public User editUser(String userId, UserEditDTO userEditDTO) {
        User userWithId= userRepositoryPort.findByIdAndDeletedFalse(userId).orElseThrow(() -> new ElementNotFoundException("User", userId));

        userWithId.setName(userEditDTO.getName());
        userWithId.setLastName(userEditDTO.getLastName());
        userRepositoryPort.save(userWithId);

        return userWithId;
    }
}
