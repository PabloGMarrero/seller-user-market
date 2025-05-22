package ar.edu.unq.seller_user.application.user;

import ar.edu.unq.seller_user.application.exceptions.EmailAlreadyInUseException;
import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.domain.port.in.user.CreateUserUseCasePort;
import ar.edu.unq.seller_user.domain.port.out.UserRepositoryPort;
import ar.edu.unq.seller_user.infrastructure.web.in.rest.dto.user.UserCreateDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateUserUseCaseAdapter implements CreateUserUseCasePort {

    private final UserRepositoryPort userRepositoryPort;

    public CreateUserUseCaseAdapter(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User createUser(UserCreateDTO userCreateDTO) {

        if(userRepositoryPort.existsByEmail(userCreateDTO.getEmail())) {
            throw new EmailAlreadyInUseException(userCreateDTO.getEmail());
        }

        String idForNewUser = UUID.randomUUID().toString();
        User user = new User(idForNewUser, userCreateDTO.getName(), userCreateDTO.getLastName(), userCreateDTO.getEmail());
        return userRepositoryPort.save(user);
    }
}
