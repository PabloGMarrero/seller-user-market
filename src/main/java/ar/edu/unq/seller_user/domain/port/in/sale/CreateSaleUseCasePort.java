package ar.edu.unq.seller_user.domain.port.in.sale;

import ar.edu.unq.seller_user.domain.model.Sale;
import ar.edu.unq.seller_user.infrastructure.web.dto.sale.SaleCreateDTO;

public interface CreateSaleUseCasePort {
    Sale createSale(SaleCreateDTO saleCreateDTO);
}
