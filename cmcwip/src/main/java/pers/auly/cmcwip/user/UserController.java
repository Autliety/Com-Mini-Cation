package pers.auly.cmcwip.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.auly.cmcwip.exceptions.CmcWebException;
import pers.auly.cmcwip.utils.JsonUtil;

@RestController("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    
    private final LoginService loginService;
    
    @RequestMapping("/login")
    public JsonNode userLogin(@RequestBody JsonNode payload) {
        String code = payload.get("code").asText();
        String sign = payload.get("sign").asText();
        String rawData = payload.get("raw-data").asText();
    
        WxLoginVo wxLoginVo = loginService.wechatLogin(code);
        if (!loginService.signCheck(sign, rawData, wxLoginVo.getSessionKey())) {
            throw new CmcWebException("Illegal login parameters");
        }
    
        String openId = wxLoginVo.getOpenId();
    
        ObjectNode response = JsonUtil.objectNode();
        
        return null;
    }
    
}
