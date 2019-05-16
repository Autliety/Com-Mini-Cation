package pers.auly.cmcwip.modules.selfqna;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Collection;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.auly.cmcwip.utils.JsonUtils;

@RestController
@RequestMapping("/selfqna")
public class SelfQnaController {
    
    private final SelfQnaService selfQnaService;
    
    private SelfQnaController(SelfQnaService selfQnaService) {
        this.selfQnaService = selfQnaService;
    }
    
    @GetMapping
    public JsonNode search(@RequestParam(name = "search", required = false) String search) {
        Collection<Document> documents = selfQnaService.search(search.split(" "));
        return JsonUtils.objectNode()
            .putPOJO("results", documents);
    }
    
    @GetMapping("/d")
    public ResponseEntity<Resource> download(@RequestParam(name = "id") int id) {
        Resource response = selfQnaService.getDocumentResource(id);
        return ResponseEntity.ok()
            .contentType(MediaType.valueOf("application/msword"))
            .body(response);
    }
    
}
