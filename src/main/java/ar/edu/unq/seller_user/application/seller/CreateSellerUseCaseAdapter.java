package ar.edu.unq.seller_user.application.seller;

import ar.edu.unq.seller_user.application.exceptions.EmailAlreadyInUseException;
import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.in.seller.CreateSellerUseCasePort;
import ar.edu.unq.seller_user.domain.port.out.SellerRepositoryPort;
import ar.edu.unq.seller_user.infrastructure.web.dto.seller.SellerCreateDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateSellerUseCaseAdapter implements CreateSellerUseCasePort {

    private final SellerRepositoryPort sellerRepositoryPort;

    public CreateSellerUseCaseAdapter(SellerRepositoryPort sellerRepositoryPort) {
        this.sellerRepositoryPort = sellerRepositoryPort;
    }

    @Override
    public Seller createSeller(SellerCreateDTO sellerCreateDTO) {

        if(sellerRepositoryPort.existsByCompanyEmail(sellerCreateDTO.getCompanyEmail())) {
            throw new EmailAlreadyInUseException(sellerCreateDTO.getCompanyEmail());
        }

        String idForNewSeller = UUID.randomUUID().toString();
        Seller seller = new Seller(idForNewSeller, sellerCreateDTO.getCompanyName(), sellerCreateDTO.getCompanyEmail());
        return sellerRepositoryPort.save(seller);
    }
}
