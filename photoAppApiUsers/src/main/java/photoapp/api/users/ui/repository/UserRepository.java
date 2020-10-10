package photoapp.api.users.ui.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import photoapp.api.users.ui.data.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
