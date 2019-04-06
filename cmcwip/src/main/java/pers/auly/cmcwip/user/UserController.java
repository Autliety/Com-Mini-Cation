package pers.auly.cmcwip.user;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public JSONObject userLogin(@RequestBody Map<String, ?> payload) {
        var data = new JSONObject();
        var code = (String) payload.get("code");
        var sig = (String) payload.get("sig");
        var raw = (String) payload.get("raw");
        System.out.println(code + sig + raw);
        return null;
    }
    
}
