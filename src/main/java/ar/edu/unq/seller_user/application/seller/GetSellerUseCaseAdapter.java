package ar.edu.unq.seller_user.application.seller;

import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.in.seller.GetSellerUseCasePort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetSellerUseCaseAdapter implements GetSellerUseCasePort {

    @Override
    public Seller getSellerById(String sellerId) {
        return new Seller(UUID.randomUUID().toString(), "mockSeller", "mockSeller@gmail.com");
    }
}
