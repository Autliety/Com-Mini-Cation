package pers.auly.cmcwip.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.List;

public class JsonUtils {
    
    private static final ObjectMapper mapper;
    private static final TypeFactory typeFactory = TypeFactory.defaultInstance();
    
    static {
        mapper = new ObjectMapper();
        // mapper settings goes here
    }
    
    // json tree methods
    public static ObjectNode objectNode() {
        return mapper.createObjectNode();
    }
    
    public static ArrayNode arrayNode() {
        return mapper.createArrayNode();
    }
    
    public static JsonNode obj2Tree(Object obj) {
        return mapper.valueToTree(obj);
    }
    
    public static <T> List<T> tree2List(JsonNode tree, Class<T> clazz) {
        return mapper.convertValue(tree, typeFactory.constructCollectionType(List.class, clazz));
    }
    
}
