package ar.edu.unq.seller_user.application.user;

import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.domain.port.out.UserRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserUseCaseAdapterTest {

    @InjectMocks
    private GetUserUseCaseAdapter getUserUseCaseAdapter;

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Test
    public void getUserByIdTest() {
        var user = mock(User.class);
        var mockUserId = UUID.randomUUID().toString();
        when(userRepositoryPort.findByIdAndDeletedFalse(mockUserId)).thenReturn(Optional.of(user));

        Optional<User> userOptional = getUserUseCaseAdapter.getUserById(mockUserId);

        assertTrue(userOptional.isPresent());
        assertEquals(user, userOptional.get());
        verify(userRepositoryPort, times(1)).findByIdAndDeletedFalse(mockUserId);
    }
}