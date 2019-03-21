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
public class Question {
    
    @Id
    @GeneratedValue
    Long id;
    Long userId;
    
    String theme;
    String context;
    
    @Builder.Default Date date = new Date();
    
    @Builder.Default Integer answerNum = 0;
    
}
