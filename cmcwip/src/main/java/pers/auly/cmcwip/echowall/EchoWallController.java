package pers.auly.cmcwip.echowall;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoWallController {
    
    private final static String PATH = "/echowall";
    
    private final EchoWallService echoWallService;
    
    @Autowired
    public EchoWallController(EchoWallService echoWallService) {
        this.echoWallService = echoWallService;
    }
    
    
    @PostMapping(PATH)
    public JSONObject createQuestion(@RequestBody Map<String, String> payload) {
        var data = new JSONObject(1);
        String theme = payload.get("theme");
        String context = payload.get("context");
        echoWallService.createQuestion(theme, context);
        return null;
    }
}
