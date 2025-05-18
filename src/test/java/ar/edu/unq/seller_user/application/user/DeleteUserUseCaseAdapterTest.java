package ar.edu.unq.seller_user.application.user;

import ar.edu.unq.seller_user.application.exceptions.ElementNotFoundException;
import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.domain.port.out.UserRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseAdapterTest {

    @InjectMocks
    private DeleteUserUseCaseAdapter deleteUserUseCaseAdapter;

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Test
    public void deleteUserTest() {
        User user = mock(User.class);
        when(userRepositoryPort.findById(anyString())).thenReturn(Optional.of(user));
        when(user.getDeleted()).thenReturn(false);

        deleteUserUseCaseAdapter.deleteUser("mockUserId");

        verify(userRepositoryPort, times(1)).findById("mockUserId");
        verify(user, times(1)).markAsDeleted();
        verify(userRepositoryPort, times(1)).save(user);
    }

    @Test
    public void deleteAlreadyDeletedUserTest() {
        User user = mock(User.class);
        when(userRepositoryPort.findById("mockUserId")).thenReturn(Optional.of(user));
        when(user.getDeleted()).thenReturn(true);

        deleteUserUseCaseAdapter.deleteUser("mockUserId");

        verify(userRepositoryPort, times(1)).findById("mockUserId");
        verify(user, never()).markAsDeleted();
        verify(userRepositoryPort, never()).save(any(User.class));
    }

    @Test
    public void deleteNonExistentUserTest() {
        when(userRepositoryPort.findById("mockUserId")).thenReturn(Optional.empty());

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> {
            deleteUserUseCaseAdapter.deleteUser("mockUserId");
        });

        assertEquals("User with Id: mockUserId not found", exception.getMessage());
        verify(userRepositoryPort, times(1)).findById("mockUserId");
        verify(userRepositoryPort, never()).save(any(User.class));
    }
}