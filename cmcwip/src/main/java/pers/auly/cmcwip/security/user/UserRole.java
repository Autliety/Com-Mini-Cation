package pers.auly.cmcwip.security.user;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    STUDENT, TEACHER;
    
    @Override
    public String getAuthority() {
        return "ROLE_" + this.toString();
    }
    
}
