package pers.auly.cmcwip.security;

import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableWebSecurity
public class CmcWebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final AuthenticationProvider wmaAuthProvider;
    
    public CmcWebSecurityConfig(
        AuthenticationProvider wmaAuthProvider) {
        this.wmaAuthProvider = wmaAuthProvider;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterAfter(wmaLoginFilter(), SecurityContextPersistenceFilter.class)
            .authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .csrf().disable();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder builder) {
        builder.authenticationProvider(wmaAuthProvider);
    }
    
    @Override
    public AuthenticationManager authenticationManagerBean() {
        try {
            return super.authenticationManagerBean();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private Filter wmaLoginFilter() {
        try {
            return ((request, response, chain) -> {
                Optional.ofNullable(((HttpServletRequest) request).getHeader("cmc-token"))
                    .ifPresent(string -> {
                        WmaLoginToken token = new WmaLoginToken(string);
                        Authentication auth = authenticationManagerBean().authenticate(token);
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    });
                chain.doFilter(request, response);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}


