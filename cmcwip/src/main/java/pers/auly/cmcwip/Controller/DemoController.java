package pers.auly.cmcwip.Controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
    private static final String PATH = "/hello";
    
    @GetMapping(PATH)
    public JSONObject getHello(@RequestBody JSONObject payload) {
        return payload;
    }
    
    @PostMapping(PATH)
    public JSONObject postHello(@RequestBody JSONObject payload) {
        JSONObject result = new JSONObject();
        System.out.println(payload.get("name").toString());
        result.put("number", "7");
        return result;
    }
    
}
