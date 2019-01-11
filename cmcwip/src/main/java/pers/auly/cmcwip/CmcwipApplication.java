package pers.auly.cmcwip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CmcwipApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CmcwipApplication.class, args);
    }
    
}

