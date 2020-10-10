package photoapp.api.users.ui.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import photoapp.api.users.ui.data.UserEntity;
import photoapp.api.users.ui.dto.UserDto;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    UserDto create(UserDto userDto);
    Iterable<UserEntity> findAll();
    UserDto getUserDetailsByEmail(String email);

    UserEntity getUser(UUID id);
    UserEntity update(UserDto userDto);
    boolean delete(UUID id);
}
