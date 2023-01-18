package de.fh_kiel.oop.controller;

import com.fasterxml.jackson.databind.*;

import java.io.*;

public class Json {

    private static Json json = null;
    private static ObjectMapper objectMapper;

    private Json() {
        objectMapper = new ObjectMapper();
        //Sorgt dafür, dass Json Attribute für die in der Klasse keine Instanzvariablen existieren, ignoriert werden
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    //Singleton
    public static Json getInstance() {
        if (json == null) {
            json = new Json();
        }

        return json;
    }

    //Dependency Injection
    public JsonNode parse(InputStream src) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(new BufferedReader(new InputStreamReader(src)));
        src.close();

        return jsonNode;
    }

    //Json Node -> Java Object
    public <T> T jsonToJavaObject(JsonNode node, Class<T> tClass) throws Exception {
        return objectMapper.treeToValue(node, tClass);
    }
}
