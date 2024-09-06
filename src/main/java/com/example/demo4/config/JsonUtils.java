package com.example.demo4.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
public class JsonUtils {
    public static boolean hasProperty(String json, String propertyName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        return node.has(propertyName);
    }
}