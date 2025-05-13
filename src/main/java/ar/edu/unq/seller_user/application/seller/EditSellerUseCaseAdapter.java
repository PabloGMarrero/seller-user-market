package ar.edu.unq.seller_user.application.seller;

import ar.edu.unq.seller_user.application.exceptions.ElementNotFoundException;
import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.in.seller.EditSellerUseCasePort;
import ar.edu.unq.seller_user.domain.port.out.SellerRepositoryPort;
import ar.edu.unq.seller_user.infrastructure.web.dto.seller.SellerEditDTO;
import org.springframework.stereotype.Service;

@Service
public class EditSellerUseCaseAdapter implements EditSellerUseCasePort {

    private final SellerRepositoryPort sellerRepositoryPort;

    public EditSellerUseCaseAdapter(SellerRepositoryPort sellerRepositoryPort) {
        this.sellerRepositoryPort = sellerRepositoryPort;
    }

    @Override
    public Seller editSeller(String sellerId, SellerEditDTO sellerEditDTO) {
        Seller sellerWithId = sellerRepositoryPort.findByIdAndDeletedFalse(sellerId).orElseThrow(() -> new ElementNotFoundException("Seller", sellerId));

        sellerWithId.setCompanyName(sellerEditDTO.getCompanyName());
        sellerRepositoryPort.save(sellerWithId);

        return sellerWithId;
    }
}
