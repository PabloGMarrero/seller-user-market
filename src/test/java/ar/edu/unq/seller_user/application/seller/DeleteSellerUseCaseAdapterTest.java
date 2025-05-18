package ar.edu.unq.seller_user.application.seller;

import ar.edu.unq.seller_user.application.exceptions.ElementNotFoundException;
import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.out.SellerRepositoryPort;
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
public class DeleteSellerUseCaseAdapterTest {

    @InjectMocks
    private DeleteSellerUseCaseAdapter deleteSellerUseCaseAdapter;

    @Mock
    private SellerRepositoryPort sellerRepositoryPort;

    @Test
    public void deleteSellerTest() {
        Seller seller = mock(Seller.class);
        when(sellerRepositoryPort.findById(anyString())).thenReturn(Optional.of(seller));
        when(seller.getDeleted()).thenReturn(false);

        deleteSellerUseCaseAdapter.deleteSeller("mockSellerId");

        verify(sellerRepositoryPort, times(1)).findById("mockSellerId");
        verify(seller, times(1)).markAsDeleted();
        verify(sellerRepositoryPort, times(1)).save(seller);
    }

    @Test
    public void deleteAlreadyDeletedSellerTest() {
        Seller seller= mock(Seller.class);
        when(sellerRepositoryPort.findById("mockSellerId")).thenReturn(Optional.of(seller));
        when(seller.getDeleted()).thenReturn(true);

        deleteSellerUseCaseAdapter.deleteSeller("mockSellerId");

        verify(sellerRepositoryPort, times(1)).findById("mockSellerId");
        verify(seller, never()).markAsDeleted();
        verify(sellerRepositoryPort, never()).save(any(Seller.class));
    }


    @Test
    public void deleteNonExistentSellerTest() {
        String nonExistentSellerId = "non-existent-id";
        when(sellerRepositoryPort.findById(nonExistentSellerId)).thenReturn(Optional.empty());

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> {
            deleteSellerUseCaseAdapter.deleteSeller(nonExistentSellerId);
        });

        assertEquals("Seller with Id: non-existent-id not found", exception.getMessage());
        verify(sellerRepositoryPort, times(1)).findById(nonExistentSellerId);
        verify(sellerRepositoryPort, never()).save(any(Seller.class));
    }
}