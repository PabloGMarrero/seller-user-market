package ar.edu.unq.seller_user.domain.port.in.user;

import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.infrastructure.web.in.dto.user.UserEditDTO;

public interface EditUserUseCasePort {
    User editUser(String userId, UserEditDTO userEditDTO);
}
