package pers.auly.cmcwip.modules.echowall;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "uid")
    @JsonIgnore // anonymous questions
    private User user;
    
    private String title;
    
    private String context;
    
    @ElementCollection
    private Set<String> tags;
    
    @UpdateTimestamp
    private Date updateTime;
}
