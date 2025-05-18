package ar.edu.unq.seller_user.application.user;

import ar.edu.unq.seller_user.application.exceptions.EmailAlreadyInUseException;
import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.domain.port.out.UserRepositoryPort;
import ar.edu.unq.seller_user.infrastructure.web.in.dto.user.UserCreateDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseAdapterTest {

    @InjectMocks
    private CreateUserUseCaseAdapter createUserUseCaseAdapter;

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Test
    public void createUserTest() {
        User user = mock(User.class);

        when(userRepositoryPort.existsByEmail(anyString())).thenReturn(false);
        when(userRepositoryPort.save(any(User.class))).thenReturn(user);

        UserCreateDTO userCreateDTO = mock(UserCreateDTO.class);
        when(userCreateDTO.getEmail()).thenReturn("mockEmail");
        createUserUseCaseAdapter.createUser(userCreateDTO);

        verify(userRepositoryPort, times(1)).existsByEmail(anyString());
        verify(userRepositoryPort, times(1)).save(any(User.class));
    }

    @Test
    public void createUserWithExistingEmailTest() {
        when(userRepositoryPort.existsByEmail(eq("mockEmail"))).thenReturn(true);

        UserCreateDTO userCreateDTO = mock(UserCreateDTO.class);
        when(userCreateDTO.getEmail()).thenReturn("mockEmail");
        EmailAlreadyInUseException exception = assertThrows(EmailAlreadyInUseException.class, () -> {
            createUserUseCaseAdapter.createUser(userCreateDTO);
        });

        assertEquals("Email: mockEmail is already in use", exception.getMessage());
        verify(userRepositoryPort, times(1)).existsByEmail("mockEmail");
        verify(userRepositoryPort, never()).save(any(User.class));
    }
}