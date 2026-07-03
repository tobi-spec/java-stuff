package com.example.springhandlefiles.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class SnakeYamlReader {
    public static void main(String[] args) throws IOException {
        Yaml yaml = new Yaml();
        try(InputStream inputStream = SnakeYamlReader.class.getClassLoader().getResourceAsStream("example.yaml")) {
            Map<String, Object> order = yaml.load(inputStream);
            System.out.println(order.toString());
        }
    }
}
