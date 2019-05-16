package pers.auly.cmcwip.security.user;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends CrudRepository<User, Integer> {
    
    Optional<User> findByOpenId(String openId);
    
}
