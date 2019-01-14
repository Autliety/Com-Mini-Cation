package pers.auly.cmcwip.Service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    
    public Integer getHelloNumber(String name) {
        return name.length();
    }
    
}
