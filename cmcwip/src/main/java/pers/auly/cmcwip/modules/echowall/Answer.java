package pers.auly.cmcwip.modules.echowall;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import pers.auly.cmcwip.security.user.User;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Answer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String context;
    
    @ManyToOne
    @JoinColumn(name = "uid")
    @JsonIgnore
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "qid")
    @JsonIgnore
    private Question question;
    
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;
}
