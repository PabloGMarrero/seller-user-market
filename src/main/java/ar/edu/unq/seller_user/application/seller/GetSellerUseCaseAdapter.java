package ar.edu.unq.seller_user.application.seller;

import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.in.seller.GetSellerUseCasePort;
import ar.edu.unq.seller_user.domain.port.out.SellerRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetSellerUseCaseAdapter implements GetSellerUseCasePort {

    private final SellerRepositoryPort sellerRepositoryPort;

    public GetSellerUseCaseAdapter(SellerRepositoryPort sellerRepositoryPort) {
        this.sellerRepositoryPort = sellerRepositoryPort;
    }

    @Override
    public Optional<Seller> getSellerById(String sellerId) {
        return sellerRepositoryPort.findByIdAndDeletedFalse(sellerId);
    }
}
