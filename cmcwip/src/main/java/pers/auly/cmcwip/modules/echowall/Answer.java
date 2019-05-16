package pers.auly.cmcwip.modules.echowall;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import pers.auly.cmcwip.security.user.User;

@Entity
@Data
@Builder
class Answer {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qid")
    private Question question;
    
    private String context;
    
    @UpdateTimestamp
    private Date updateTime;
}
