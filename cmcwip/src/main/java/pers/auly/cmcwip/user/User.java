package pers.auly.cmcwip.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
class User {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private String realName;
    
    private String schoolId;
    
    // wechat user specific open-id
    private String openId;
    
}
