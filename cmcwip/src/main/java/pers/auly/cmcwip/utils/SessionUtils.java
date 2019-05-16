package pers.auly.cmcwip.utils;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pers.auly.cmcwip.utils.exceptions.UnauthorizedException;
import pers.auly.cmcwip.security.user.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SessionUtils {
    
    public static User current() {
        return (User) Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
            .map(Authentication::getPrincipal)
            .orElseThrow(UnauthorizedException::new);
    }
    
}
