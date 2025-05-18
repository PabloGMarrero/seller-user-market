package ar.edu.unq.seller_user.application.seller;

import ar.edu.unq.seller_user.application.exceptions.ElementNotFoundException;
import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.in.seller.DeleteSellerUseCasePort;
import ar.edu.unq.seller_user.domain.port.out.SellerRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteSellerUseCaseAdapter implements DeleteSellerUseCasePort {

    private final SellerRepositoryPort sellerRepositoryPort;

    public DeleteSellerUseCaseAdapter(SellerRepositoryPort sellerRepositoryPort) {
        this.sellerRepositoryPort = sellerRepositoryPort;
    }

    @Override
    public void deleteSeller(String sellerId) {
        Seller sellerWithId = sellerRepositoryPort.findById(sellerId).orElseThrow(() -> new ElementNotFoundException("Seller", sellerId));

        if(!sellerWithId.getDeleted()){
            sellerWithId.markAsDeleted();
            sellerRepositoryPort.save(sellerWithId);
        }
    }
}
