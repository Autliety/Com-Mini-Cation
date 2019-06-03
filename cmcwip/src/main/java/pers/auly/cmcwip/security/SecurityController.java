package pers.auly.cmcwip.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    
    @PostMapping("/register")
    public JsonNode userRegister(@RequestBody ObjectNode payload) {
        throw new UnsupportedOperationException();
    }
    
    @GetMapping
    public JsonNode getUserInfo() {
        return JsonUtils.objectNode()
            .putPOJO("user", SessionUtils.current());
    }
}
