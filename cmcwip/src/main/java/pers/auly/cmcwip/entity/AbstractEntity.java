package pers.auly.cmcwip.entity;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;

public interface AbstractEntity extends Serializable {
    
    long serialVersionUID = 1;
    
    default JSONObject toJson() {
        // TODO toJsonObject
        return null;
    }
    
}
