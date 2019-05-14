package pers.auly.cmcwip.selfqna;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.auly.cmcwip.utils.JsonUtils;

@RestController
@RequestMapping("/selfqna")
public class SelfQnaController {
    
    @GetMapping("/tags")
    public JsonNode getTags() {
        // get default tags
        return null;
    }
    
    @PostMapping("/question")
    public JsonNode question(@RequestBody ObjectNode payload) {
        String title = payload.get("title").asText();
        String context = payload.get("context").asText();
        List<String> tags = JsonUtils.tree2List(payload.get("tags"), String.class);
        
        return null;
    }
    
    @PostMapping("/answer")
    public JsonNode answer(@RequestBody ObjectNode payload) {
        int qId = payload.get("question-id").asInt();
        String context = payload.get("context").asText();
        
        return null;
    }
    
}
