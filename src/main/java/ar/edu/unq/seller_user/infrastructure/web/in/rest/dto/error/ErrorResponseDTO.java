package ar.edu.unq.seller_user.infrastructure.web.in.rest.dto.error;

import lombok.Getter;

@Getter
public abstract class ErrorResponseDTO<T> {

    private T message;

    public ErrorResponseDTO(T message) {
        this.message = message;
    }
}
