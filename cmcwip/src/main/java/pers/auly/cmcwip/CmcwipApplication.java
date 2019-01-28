package pers.auly.cmcwip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pers.auly.cmcwip.service.FileCopyService;

@SpringBootApplication
public class CmcwipApplication {
    
    public final FileCopyService fileCopyService;
    
    @Autowired
    public CmcwipApplication(FileCopyService fileCopyService) {
        this.fileCopyService = fileCopyService;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(CmcwipApplication.class, args);
    }
    
    @Bean
    public ApplicationRunner init() {
        return args -> {
            fileCopyService.init();
        };
    }
    
}
