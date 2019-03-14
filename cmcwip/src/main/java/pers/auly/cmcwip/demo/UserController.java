package pers.auly.cmcwip.demo;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    private static final String PATH = "/login";
    
    @PostMapping(PATH)
    public JSONObject getUserInfo(@RequestBody Map<String, String> paylaod) {
        var data = new JSONObject(2);
        User userData = userService.login(paylaod.get("sig"), paylaod.get("name"));
        data.put("name", userData.getRealName());
        data.put("freq", userData.getFreq());
        return data;
    }
    
}
