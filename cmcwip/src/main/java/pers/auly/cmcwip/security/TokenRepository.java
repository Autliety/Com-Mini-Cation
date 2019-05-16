package pers.auly.cmcwip.security;

import org.springframework.data.repository.CrudRepository;
import pers.auly.cmcwip.security.user.User;

interface TokenRepository extends CrudRepository<WmaLoginToken, String> {
    
    void deleteByUser(User user);
    
}
