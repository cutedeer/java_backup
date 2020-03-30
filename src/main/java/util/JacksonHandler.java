package util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 */
public final class JacksonHandler {

    private static final Logger logger = LoggerFactory.getLogger(JacksonHandler.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

        mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
        mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    }

    private JacksonHandler() {
    }

    public static String toSerialize(Object data) {
        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            logger.error("toSerialize error", e);
            return null;
        }
    }

    public static <T> T deSerialize(String content, Class<T> clazz) {
        try {
            return mapper.readValue(content, clazz);
        } catch (IOException e) {
            logger.error("deSerialize error: {}", content, e);
            return null;
        }
    }

    public static <T> Map<String, T> deSerializeMap(Class<T> clazeValue, String content) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametrizedType(Map.class, Map.class, String.class, clazeValue);
            return mapper.readValue(content, javaType);
        } catch (IOException e) {
            logger.error("deserialize with java type object error: {}", content, e);
            return null;
        }
    }

    public static <T> List<T> deSerializeList(String json, Class<T> elementClasse) {
        List<T> t = Lists.newArrayList();
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametrizedType(List.class, List.class, elementClasse);
            t = mapper.readValue(json, javaType);
        } catch (IOException e) {
            logger.error("deserialize with java type object error: {}", json, e);
        }
        return t;
    }

}
