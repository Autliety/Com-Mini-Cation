package pers.auly.cmcwip.demo;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<User, Integer> {
    
    Optional<User> findByName(String username);

}
