package ar.edu.unq.seller_user.infrastructure.web;

import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.in.seller.CreateSellerUseCasePort;
import ar.edu.unq.seller_user.domain.port.in.seller.DeleteSellerUseCasePort;
import ar.edu.unq.seller_user.domain.port.in.seller.EditSellerUseCasePort;
import ar.edu.unq.seller_user.infrastructure.web.dto.seller.SellerCreateDTO;
import ar.edu.unq.seller_user.infrastructure.web.dto.seller.SellerEditDTO;
import ar.edu.unq.seller_user.infrastructure.web.dto.seller.SellerResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    private final CreateSellerUseCasePort createSellerUseCasePort;
    private final DeleteSellerUseCasePort deleteSellerUseCasePort;
    private final EditSellerUseCasePort editSellerUseCasePort;

    public SellerController(
            CreateSellerUseCasePort createSellerUseCasePort,
            DeleteSellerUseCasePort deleteSellerUseCasePort,
            EditSellerUseCasePort editSellerUseCasePort
    ) {
        this.createSellerUseCasePort = createSellerUseCasePort;
        this.deleteSellerUseCasePort = deleteSellerUseCasePort;
        this.editSellerUseCasePort = editSellerUseCasePort;
    }

    @PostMapping
    public ResponseEntity<SellerResponseDTO> createSeller(@Valid @RequestBody SellerCreateDTO sellerCreateDTO) {
        Seller createdSeller = createSellerUseCasePort.createSeller(sellerCreateDTO);

        return ResponseEntity.ok(generateSellerResponseDTOFrom(createdSeller));
    }

    @DeleteMapping("/{sellerId}")
    public ResponseEntity<Void> deleteSeller(@PathVariable String sellerId) {
        deleteSellerUseCasePort.deleteSeller(sellerId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{sellerId}")
    public ResponseEntity<SellerResponseDTO> editSeller(@PathVariable String sellerId, @Valid @RequestBody SellerEditDTO sellerEditDTO) {
        Seller editedSeller = editSellerUseCasePort.editSeller(sellerId, sellerEditDTO);

        return ResponseEntity.ok(generateSellerResponseDTOFrom(editedSeller));
    }

    private SellerResponseDTO generateSellerResponseDTOFrom(Seller seller) {
        return new SellerResponseDTO(
                seller.getId(),
                seller.getCompanyName(),
                seller.getCompanyEmail()
        );
    }
}
