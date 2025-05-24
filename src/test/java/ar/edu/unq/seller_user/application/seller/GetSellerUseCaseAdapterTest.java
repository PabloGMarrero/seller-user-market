package ar.edu.unq.seller_user.application.seller;

import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.out.SellerRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetSellerUseCaseAdapterTest {

    @InjectMocks
    private GetSellerUseCaseAdapter getSellerUseCaseAdapter;

    @Mock
    private SellerRepositoryPort sellerRepositoryPort;

    @Test
    public void getSellerByIdTest() {
        Seller mockSeller = mock(Seller.class);
        when(sellerRepositoryPort.findByIdAndDeletedFalse("mockSellerId")).thenReturn(Optional.of(mockSeller));

        Optional<Seller> sellerOptional = getSellerUseCaseAdapter.getSellerById("mockSellerId");

        assertEquals(mockSeller, sellerOptional.get());
        verify(sellerRepositoryPort, times(1)).findByIdAndDeletedFalse("mockSellerId");
    }
}