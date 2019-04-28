package pers.auly.cmcwip.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class WxLoginVo {
    
    @JsonProperty("openid")
    private String openId;
    
    @JsonProperty("session_key")
    private String sessionKey;
    
}
