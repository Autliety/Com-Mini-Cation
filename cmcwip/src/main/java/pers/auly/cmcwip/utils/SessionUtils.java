package pers.auly.cmcwip.utils;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pers.auly.cmcwip.security.user.User;
import pers.auly.cmcwip.utils.exceptions.ForbiddenException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SessionUtils {
    
    public static User current() {
        try {
            Object principal = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .orElseThrow();
            return (User) principal;
        } catch (Exception e) {
            throw new ForbiddenException();
        }
    }
    
}
