package pers.auly.cmcwip.Controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.auly.cmcwip.Service.HelloService;

@RestController
public class DemoController {
    
    private final HelloService helloService;
    
    @Autowired
    public DemoController(HelloService helloService) {
        this.helloService = helloService;
    }
    
    private static final String PATH = "/hello";
    
    @GetMapping(PATH)
    public JSONObject getHello(@RequestParam("name") String name) {
        JSONObject res = new JSONObject();
        res.put("number", helloService.getHelloNumber(name));
        return res;
    }
    
    
}
