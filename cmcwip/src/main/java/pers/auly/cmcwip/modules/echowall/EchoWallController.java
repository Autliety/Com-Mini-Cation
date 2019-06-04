package pers.auly.cmcwip.modules.echowall;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.auly.cmcwip.utils.JsonUtils;
import pers.auly.cmcwip.utils.SessionUtils;

@RestController
@RequestMapping("/echowall")
public class EchoWallController {
    
    private final EchoWallService echoWallService;
    
    private EchoWallController(EchoWallService echoWallService) {
        this.echoWallService = echoWallService;
    }
    
    @GetMapping
    public JsonNode getQuestionList() {
        final List<Question> questions;
        if (SessionUtils.isTeacher()) {
            questions = echoWallService.getAllQuestions();
        } else {
            questions = echoWallService.getMyQuestions();
        }
        return JsonUtils.objectNode()
            .putPOJO("questions", questions);
    }
    
    @GetMapping("/{id}")
    public JsonNode getQuestion(@PathVariable("id") int id) {
        Question question = echoWallService.getQuestion(id);
        List<Answer> answers = echoWallService.getAnswersForQuestion(question);
        return JsonUtils.objectNode()
            .putPOJO("question", question)
            .putPOJO("answers", answers);
    }
    
    @PutMapping
    public void putQuestion(@RequestBody ObjectNode payload) {
        String title = payload.get("title").asText();
        String context = payload.get("context").asText();
        Set<String> tags = JsonUtils.tree2Set(payload.get("tags"), String.class);
        
        echoWallService.createQuestion(title, context, tags);
    }
    
    @PostMapping("/{id}")
    public void answer(@PathVariable("id") int id, @RequestBody ObjectNode payload) {
        String context = payload.get("context").asText();
        
        Question question = echoWallService.getQuestion(id);
        echoWallService.answerQuestion(question, context);
    }
    
}
