package ar.edu.unq.seller_user.infrastructure.web.in.rest;

import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.domain.port.in.user.CreateUserUseCasePort;
import ar.edu.unq.seller_user.domain.port.in.user.DeleteUserUseCasePort;
import ar.edu.unq.seller_user.domain.port.in.user.EditUserUseCasePort;
import ar.edu.unq.seller_user.infrastructure.web.in.rest.dto.user.UserCreateDTO;
import ar.edu.unq.seller_user.infrastructure.web.in.rest.dto.user.UserEditDTO;
import ar.edu.unq.seller_user.infrastructure.web.in.rest.dto.user.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CreateUserUseCasePort createUserUseCasePort;
    private final DeleteUserUseCasePort deleteUserUseCasePort;
    private final EditUserUseCasePort editUserUseCasePort;

    public UserController(
            CreateUserUseCasePort createUserUseCasePort,
            DeleteUserUseCasePort deleteUserUseCasePort,
            EditUserUseCasePort editUserUseCasePort
    ) {
        this.createUserUseCasePort = createUserUseCasePort;
        this.deleteUserUseCasePort = deleteUserUseCasePort;
        this.editUserUseCasePort = editUserUseCasePort;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        User createdUser = createUserUseCasePort.createUser(userCreateDTO);

        return ResponseEntity.ok(generateUserResponseDTOFrom(createdUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        deleteUserUseCasePort.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> editUser(@PathVariable String userId, @Valid @RequestBody UserEditDTO userEditDTO) {
        User editedUser = editUserUseCasePort.editUser(userId, userEditDTO);

        return ResponseEntity.ok(generateUserResponseDTOFrom(editedUser));
    }

    private UserResponseDTO generateUserResponseDTOFrom(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
