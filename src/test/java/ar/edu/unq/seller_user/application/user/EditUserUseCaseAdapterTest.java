package ar.edu.unq.seller_user.application.user;

import ar.edu.unq.seller_user.application.exceptions.ElementNotFoundException;
import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.domain.port.out.UserRepositoryPort;
import ar.edu.unq.seller_user.infrastructure.web.dto.user.UserEditDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EditUserUseCaseAdapterTest {

    @InjectMocks
    private EditUserUseCaseAdapter editUserUseCaseAdapter;

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Test
    public void editUserTest() {
        User user = mock(User.class);

        when(userRepositoryPort.findByIdAndDeletedFalse(anyString())).thenReturn(Optional.of(user));
        when(userRepositoryPort.save(any(User.class))).thenReturn(user);

        UserEditDTO userEditDTO = mock(UserEditDTO.class);
        when(userEditDTO.getName()).thenReturn("mockName");
        when(userEditDTO.getLastName()).thenReturn("mockLastName");
        editUserUseCaseAdapter.editUser("mockUserId", userEditDTO);

        verify(userRepositoryPort, times(1)).findByIdAndDeletedFalse("mockUserId");
        verify(user, times(1)).setName(anyString());
        verify(user, times(1)).setLastName(anyString());
        verify(userRepositoryPort, times(1)).save(user);
    }

    @Test
    public void editNonExistentUserTest() {
        when(userRepositoryPort.findByIdAndDeletedFalse("mockUserId")).thenReturn(Optional.empty());

        UserEditDTO userEditDTO = mock(UserEditDTO.class);
        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> {
            editUserUseCaseAdapter.editUser("mockUserId", userEditDTO);
        });

        assertEquals("User with Id: mockUserId not found", exception.getMessage());
        verify(userRepositoryPort, times(1)).findByIdAndDeletedFalse("mockUserId");
        verify(userRepositoryPort, never()).save(any(User.class));
    }

}