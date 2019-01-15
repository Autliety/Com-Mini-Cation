package pers.auly.cmcwip.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.auly.cmcwip.entity.User;
import pers.auly.cmcwip.service.UserService;

@RestController
public class UserController {
    
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    private static final String PATH = "/hello";
    
    @GetMapping(PATH)
    public JSONObject getUserInfo(@RequestParam("name") String name) {
        JSONObject data = new JSONObject();
        User user = userService.getUserByName(name);
        data.put("id", user.getId());
        data.put("freq", user.getFreq());
        return data;
    }
    
    
}
