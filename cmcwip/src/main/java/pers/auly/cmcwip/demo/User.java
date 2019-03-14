package pers.auly.cmcwip.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class User {
    
    @Id
    private String signature;
    
    private String nickName;
    
    @Builder.Default
    private String realName = "学生";
    
    @Builder.Default
    private Integer freq = 1;
    
    public User setSignature(String signature) {
        this.signature = signature;
        return this;
    }
    
    public User setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
    
    public User setRealName(String realName) {
        this.realName = realName;
        return this;
    }
    
    public User setFreq(Integer freq) {
        this.freq = freq;
        return this;
    }
}
