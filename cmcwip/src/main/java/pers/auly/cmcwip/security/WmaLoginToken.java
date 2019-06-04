package pers.auly.cmcwip.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import pers.auly.cmcwip.security.user.User;

@Entity(name = "token")
@Getter(AccessLevel.PACKAGE)
class WmaLoginToken implements Authentication {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private String token;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @CreationTimestamp
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date creationTime;
    
    @SuppressWarnings("unused")
    private WmaLoginToken() {
    
    }
    
    WmaLoginToken(String token) {
        this.token = token;
        this.user = null;
    }
    
    WmaLoginToken(String token, User user) {
        this.user = user;
        this.token = token;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user == null) {
            throw new IllegalStateException();
        }
        return user.getRoles();
    }
    
    @Override
    public Object getCredentials() {
        return token;
    }
    
    @Override
    public Object getPrincipal() {
        if (user == null) {
            throw new IllegalStateException();
        }
        return user;
    }
    
    @Override
    public Object getDetails() {
        return getPrincipal();
    }
    
    @Override
    public boolean isAuthenticated() {
        return user != null;
    }
    
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }
    
    @Override
    public String getName() {
        if (user == null) {
            throw new IllegalStateException();
        }
        return user.getId().toString();
    }
}
