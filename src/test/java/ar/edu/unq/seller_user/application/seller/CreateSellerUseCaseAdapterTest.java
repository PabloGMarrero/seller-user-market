package ar.edu.unq.seller_user.application.seller;

import ar.edu.unq.seller_user.application.exceptions.EmailAlreadyInUseException;
import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.out.SellerRepositoryPort;
import ar.edu.unq.seller_user.infrastructure.web.dto.seller.SellerCreateDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateSellerUseCaseAdapterTest {

    @InjectMocks
    private CreateSellerUseCaseAdapter createSellerUseCaseAdapter;

    @Mock
    private SellerRepositoryPort sellerRepositoryPort;

    @Test
    public void createSellerTest() {
        when(sellerRepositoryPort.existsByCompanyEmail(anyString())).thenReturn(false);
        Seller mockSeller = mock(Seller.class);
        when(sellerRepositoryPort.save(any(Seller.class))).thenReturn(mockSeller);

        SellerCreateDTO mockSellerCreateDTO = mock(SellerCreateDTO.class);
        when(mockSellerCreateDTO.getCompanyEmail()).thenReturn("mockCompanyEmail");
        Seller result = createSellerUseCaseAdapter.createSeller(mockSellerCreateDTO);

        assertEquals(mockSeller, result);

        verify(sellerRepositoryPort, times(1)).existsByCompanyEmail(mockSellerCreateDTO.getCompanyEmail());
        verify(sellerRepositoryPort, times(1)).save(any(Seller.class));
    }

    @Test
    public void createSellerWithExistingCompanyNameTest() {
        when(sellerRepositoryPort.existsByCompanyEmail(anyString())).thenReturn(true);

        SellerCreateDTO mockSellerCreateDTO = mock(SellerCreateDTO.class);
        when(mockSellerCreateDTO.getCompanyEmail()).thenReturn("mockCompanyEmail");

        EmailAlreadyInUseException exception = assertThrows(EmailAlreadyInUseException.class, () -> {
            createSellerUseCaseAdapter.createSeller(mockSellerCreateDTO);
        });

        assertEquals("Email: mockCompanyEmail is already in use", exception.getMessage());
        verify(sellerRepositoryPort, times(1)).existsByCompanyEmail(mockSellerCreateDTO.getCompanyEmail());
        verify(sellerRepositoryPort, never()).save(any(Seller.class));
    }
}