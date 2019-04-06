package pers.auly.cmcwip.user;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<User, String> {
    
    Optional<User> findBySignature(String sig);

}
