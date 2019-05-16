package pers.auly.cmcwip.modules.selfqna;

import java.nio.file.Path;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
class Document {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private String title;
    
    private Path Path;
    
    private String summary;
    
}
