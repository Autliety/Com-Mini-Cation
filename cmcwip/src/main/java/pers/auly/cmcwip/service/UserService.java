package pers.auly.cmcwip.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    
    private final Map<String, Integer> helloMap = new HashMap<>();
    private final AtomicInteger count = new AtomicInteger();
    
    public Integer getHelloNumber(String name) {
        if (helloMap.containsKey(name)) {
            return helloMap.get(name);
        }
        int newCount = count.addAndGet(1);
        helloMap.put(name, newCount);
        return newCount;
    }
    
}
