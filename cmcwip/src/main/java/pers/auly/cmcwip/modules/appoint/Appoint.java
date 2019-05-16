package pers.auly.cmcwip.modules.appoint;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import pers.auly.cmcwip.security.user.User;

@Entity
@Data
@Builder
class Appoint {
    
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private Date appointDate;
    
    @Enumerated(EnumType.ORDINAL)
    private AppointTime time;
    
    private String summary;
    
    @ManyToOne
    @JoinColumn(name = "dist_user")
    private User distUser;
    
    @Enumerated(EnumType.ORDINAL)
    private AppointStatus status;
    
}
