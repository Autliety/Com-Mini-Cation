package pers.auly.cmcwip.echowall;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Answer {
    
    @Id
    @GeneratedValue
    Long id;
    
    Long userId;
    
    Long questionId;
    
    String context;
    
    Date date;
    
}
