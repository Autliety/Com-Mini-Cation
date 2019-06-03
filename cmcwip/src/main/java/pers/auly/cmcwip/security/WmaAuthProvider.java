package pers.auly.cmcwip.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
class WmaAuthProvider implements AuthenticationProvider {
    
    private final TokenRepository tokenRepository;
    
    public WmaAuthProvider(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        WmaLoginToken wmaLoginToken = (WmaLoginToken) authentication;
        return tokenRepository.findByToken(wmaLoginToken.getToken())
            .orElseThrow(() -> new BadCredentialsException("incorrect session token! "));
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return WmaLoginToken.class.isAssignableFrom(authentication);
    }
}
