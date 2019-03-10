package pers.auly.cmcwip.demo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    private static final String PATH = "/hello";
    
    @GetMapping(PATH)
    public JSONObject getUserInfo(@RequestParam("name") String username) {
        var data = new JSONObject(2);
        User userData = userService.login(username);
        data.put("id", userData.getId());
        data.put("freq", userData.getFreq());
        return data;
    }
    
    
}
