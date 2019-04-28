package pers.auly.cmcwip.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {
    
    private static final ObjectMapper mapper;
    
    static {
        mapper = new ObjectMapper();
        // Jackson mapper configurations
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }
    
    
    /**
     * @return global singleton {@link ObjectMapper} reference
     * @deprecated if possible, use other methods determined in this class
     */
    @Deprecated
    public static ObjectMapper mapper() {
        return mapper;
    }
    
    // Json tree constructor
    public static ObjectNode objectNode() {
        return mapper.createObjectNode();
    }
    
    public static ArrayNode arrayNode() {
        return mapper.createArrayNode();
    }
    
    // Serialize functions
    public static String toJsonString(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }
    
    // Deserialize functions
    public static <T> T string2Pojo(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }
    
    public static JsonNode string2Tree(String json) throws IOException {
        return mapper.readTree(json);
    }
    
    // Convert functions
    public static <T> T tree2Pojo(JsonNode tree, Class<T> clazz) {
        try {
            return mapper.treeToValue(tree, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    public static JsonNode pojo2Tree(Object obj) {
        return mapper.valueToTree(obj);
    }
    
}
