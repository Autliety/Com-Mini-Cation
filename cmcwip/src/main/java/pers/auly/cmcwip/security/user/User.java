package pers.auly.cmcwip.security.user;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private String realName;
    
    private String schoolId;
    
    private String phoneNum;
    
    @Column(unique = true)
    private String openId; // wechat user specific open-id
   
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    private Set<? extends UserRole> roles;
    
}
