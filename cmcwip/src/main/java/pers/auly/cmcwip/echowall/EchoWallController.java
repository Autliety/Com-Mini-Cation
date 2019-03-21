package pers.auly.cmcwip.echowall;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoWallController {
    
    private final static String PATH = "/echowall";
    
    private final EchoWallService echoWallService;
    
    public EchoWallController(EchoWallService echoWallService) {
        this.echoWallService = echoWallService;
    }
    
    
    @PostMapping(PATH)
    public JSONObject createQuestion(@RequestBody Map<String, String> payload) {
        var data = new JSONObject(1);
        String theme = payload.get("theme");
        String context = payload.get("context");
        if (echoWallService.createQuestion(theme, context)) {
            return data.fluentPut("Status", "Success");
        }
        return data.fluentPut("Status", "Failed");
    }
}
