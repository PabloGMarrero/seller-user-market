package ar.edu.unq.seller_user.infrastructure.web.dto.error;

public class GenericErrorResponseDTO extends ErrorResponseDTO<String> {
    public GenericErrorResponseDTO(String message) {
        super(message);
    }
}
