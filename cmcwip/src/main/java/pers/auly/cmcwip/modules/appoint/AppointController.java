package pers.auly.cmcwip.modules.appoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.auly.cmcwip.security.user.UserRole;
import pers.auly.cmcwip.utils.JsonUtils;
import pers.auly.cmcwip.utils.SessionUtils;
import pers.auly.cmcwip.utils.exceptions.BadRequestException;

@RestController
@RequestMapping("/appoint")
public class AppointController {
    
    private final AppointService appointService;
    
    private AppointController(AppointService appointService) {
        this.appointService = appointService;
    }
    
    @GetMapping("/teachers")
    public JsonNode getAvailableTeachers() {
        return JsonUtils.objectNode()
            .putPOJO("teachers", appointService.getTeachers());
    }
    
    @GetMapping
    public JsonNode getMyAppoints() {
        List<Appoint> appoints = appointService.getMyAppoints(SessionUtils.isTeacher());
        return JsonUtils.objectNode()
            .putPOJO("appoints", appoints);
    }
    
    @PutMapping
    public void createAppoint(@RequestBody ObjectNode payload) {
        final String summary = payload.get("summary").asText().trim();
        final int distId = payload.get("tid").asInt();
        final Date date;
        final AppointTime time;
        try {
            date = new SimpleDateFormat("yyyy/M/d").parse(payload.get("date").asText());
            time = Enum.valueOf(AppointTime.class, payload.get("time").asText().toUpperCase());
        } catch (ParseException | IllegalArgumentException e) {
            throw new BadRequestException();
        }
        appointService.createAppoint(summary, distId, date, time);
    }
    
    @PostMapping("/{id}")
    public void respondAppoint(@PathVariable("id") int id, @RequestBody ObjectNode payload) {
        AppointStatus status;
        try {
            status = Enum.valueOf(AppointStatus.class, payload.get("status").asText().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException();
        }
        appointService.respondAppoint(id, status);
    }
}
