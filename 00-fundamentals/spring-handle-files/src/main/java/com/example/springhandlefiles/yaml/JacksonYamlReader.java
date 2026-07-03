package com.example.springhandlefiles.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class JacksonYamlReader {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        Order order = mapper.readValue(new File("spring-stuff/spring-handle-files/src/main/resources/example.yaml"), Order.class);
        System.out.println(order.toString());
    }
}
