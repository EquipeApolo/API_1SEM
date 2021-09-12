package br.gov.sp.fatec.utils.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONParser.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public JSONParser() {
    }

    public static <T> String toJSON(T model) {
        try {
            return objectMapper.writeValueAsString(model);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            LOGGER.error("ERROR WHILE PARSING MODEL TO JSON", var2);
            return null;
        }
    }

    public static <T> T toModel(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception var3) {
            var3.printStackTrace();
            LOGGER.error("ERROR WHILE PARSING JSON TO MODEL", var3);
            return null;
        }
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }
}