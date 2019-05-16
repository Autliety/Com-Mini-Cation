package pers.auly.cmcwip.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pers.auly.cmcwip.security.user.User;

@Component
interface TokenRepository extends CrudRepository<WmaLoginToken, String> {
    
    void deleteByUser(User user);
    
}
