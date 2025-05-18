package ar.edu.unq.seller_user.application.seller;

import ar.edu.unq.seller_user.application.exceptions.ElementNotFoundException;
import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.out.SellerRepositoryPort;
import ar.edu.unq.seller_user.infrastructure.web.in.dto.seller.SellerEditDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EditSellerUseCaseAdapterTest {

    @InjectMocks
    private EditSellerUseCaseAdapter editSellerUseCaseAdapter;

    @Mock
    private SellerRepositoryPort sellerRepositoryPort;

    @Test
    public void editSellerSuccessfullyTest() {
        Seller seller = mock(Seller.class);

        when(sellerRepositoryPort.findByIdAndDeletedFalse("mockSellerId")).thenReturn(Optional.of(seller));
        when(sellerRepositoryPort.save(seller)).thenReturn(seller);

        SellerEditDTO sellerEditDTO = mock(SellerEditDTO.class);
        Seller result = editSellerUseCaseAdapter.editSeller("mockSellerId", sellerEditDTO);

        verify(sellerRepositoryPort).findByIdAndDeletedFalse("mockSellerId");
        verify(seller).setCompanyName(sellerEditDTO.getCompanyName());
        verify(sellerRepositoryPort).save(seller);
        assertNotNull(result);
        assertEquals(seller, result);
    }

    @Test
    public void editNonExistentSellerTest() {

        when(sellerRepositoryPort.findByIdAndDeletedFalse("mockSellerId")).thenReturn(Optional.empty());

        SellerEditDTO sellerEditDTO = mock(SellerEditDTO.class);
        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> {
            editSellerUseCaseAdapter.editSeller("mockSellerId", sellerEditDTO);
        });

        assertEquals("Seller with Id: mockSellerId not found", exception.getMessage());
        verify(sellerRepositoryPort).findByIdAndDeletedFalse("mockSellerId");
        verify(sellerRepositoryPort, never()).save(any(Seller.class));
    }

    @Test
    public void editDeletedSellerTest() {
        when(sellerRepositoryPort.findByIdAndDeletedFalse("mockSellerId")).thenReturn(Optional.empty());

        SellerEditDTO sellerEditDTO = mock(SellerEditDTO.class);
        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> {
            editSellerUseCaseAdapter.editSeller("mockSellerId", sellerEditDTO);
        });

        assertEquals("Seller with Id: mockSellerId not found", exception.getMessage());
        verify(sellerRepositoryPort).findByIdAndDeletedFalse("mockSellerId");
        verify(sellerRepositoryPort, never()).save(any(Seller.class));
    }
}