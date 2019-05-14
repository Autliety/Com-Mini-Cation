package pers.auly.cmcwip.user;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    STUDENT, TEACHER, ADMIN;
    
    @Override
    public String getAuthority() {
        return "ROLE_" + this.toString();
    }
    
}
