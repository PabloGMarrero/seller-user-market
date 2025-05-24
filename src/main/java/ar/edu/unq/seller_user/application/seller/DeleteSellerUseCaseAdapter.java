package ar.edu.unq.seller_user.application.seller;

import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.in.seller.DeleteSellerUseCasePort;
import ar.edu.unq.seller_user.domain.port.out.SellerRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteSellerUseCaseAdapter implements DeleteSellerUseCasePort {

    private final SellerRepositoryPort sellerRepositoryPort;

    public DeleteSellerUseCaseAdapter(SellerRepositoryPort sellerRepositoryPort) {
        this.sellerRepositoryPort = sellerRepositoryPort;
    }

    @Override
    public void deleteSeller(String sellerId) {
        Optional<Seller> sellerOptional = sellerRepositoryPort.findByIdAndDeletedFalse(sellerId);

        if(sellerOptional.isPresent()) {
            Seller sellerWithId = sellerOptional.get();
            sellerWithId.markAsDeleted();
            sellerRepositoryPort.save(sellerWithId);
        }
    }
}
