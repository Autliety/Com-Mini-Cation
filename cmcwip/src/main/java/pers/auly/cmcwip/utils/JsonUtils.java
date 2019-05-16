package pers.auly.cmcwip.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtils {
    
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
    
    public static <T extends JsonNode> T obj2Node(Object obj) {
        return mapper.valueToTree(obj);
    }
    
    public static <T> Set<T> tree2Set(JsonNode tree, Class<T> clazz) {
        return mapper.convertValue(tree, typeFactory.constructCollectionType(Set.class, clazz));
    }
    
}
