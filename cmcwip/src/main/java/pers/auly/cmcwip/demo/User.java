package pers.auly.cmcwip.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class User {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private String name;
    
    private Integer freq = 1;
    
}
