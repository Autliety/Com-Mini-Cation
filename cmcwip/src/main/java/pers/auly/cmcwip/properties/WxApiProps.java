package pers.auly.cmcwip.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("custom.wechat")
@EnableConfigurationProperties(WxApiProps.class)
@Data
public class WxApiProps {
    
    private String apiHost;
    
    private String appId;
    
    private String secret;
    
}
