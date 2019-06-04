package pers.auly.cmcwip.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.auly.cmcwip.security.user.User;
import pers.auly.cmcwip.utils.JsonUtils;
import pers.auly.cmcwip.utils.SessionUtils;

@RestController
@RequestMapping("/user")
public class SecurityController {
    
    private final SecurityService securityService;
    
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }
    
    @PostMapping("/login")
    public JsonNode userLogin(@RequestBody ObjectNode payload) {
        String code = payload.get("code").asText();
        String sign = payload.get("sign").asText();
        String rawData = payload.get("rawData").asText();
        
        WmaLoginToken token = securityService.userLogin(code, sign, rawData);
        
        ObjectNode response = JsonUtils.objectNode();
        response.set("user", JsonUtils.obj2Node(token.getUser()));
        response.put("token", token.getToken());
        
        return response;
    }
    
    @PutMapping
    public void userRegister(@RequestBody ObjectNode payload) {
        String code = payload.get("code").asText();
        String name = payload.get("realName").asText();
        String id = payload.get("schoolId").asText();
        String phone = payload.get("phoneNum").asText();
        boolean isTeacher = payload.get("isTeacher").asBoolean();
    
        User newUser = User.builder().realName(name).schoolId(id).phoneNum(phone).build();
    
        securityService.userRegist(newUser, code, isTeacher);
    }
    
    @GetMapping
    public JsonNode getUserInfo() {
        return JsonUtils.objectNode()
            .putPOJO("user", SessionUtils.current());
    }
}
