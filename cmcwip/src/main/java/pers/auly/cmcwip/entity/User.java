package pers.auly.cmcwip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements AbstractEntity {
    
    private Integer id;
    private String name;
    private Integer freq;
    
    public User addFreq() {
        this.freq++;
        return this;
    }
}
