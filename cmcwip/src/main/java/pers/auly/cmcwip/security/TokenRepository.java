package pers.auly.cmcwip.security;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import pers.auly.cmcwip.security.user.User;

interface TokenRepository extends CrudRepository<WmaLoginToken, String> {
    
    @Modifying
    @Transactional
    void deleteByUser(User user);
    
}
