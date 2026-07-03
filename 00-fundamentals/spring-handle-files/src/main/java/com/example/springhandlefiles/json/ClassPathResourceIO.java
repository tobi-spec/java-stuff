package com.example.springhandlefiles.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.IOException;

public class ClassPathResourceIO {
    /**
     * Spring way to read file from resources folder
     */
    public static void main(String[] args) throws IOException {
        Resource jsonResource = new ClassPathResource("example.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonExample jsonExample = objectMapper.readValue(jsonResource.getInputStream(), JsonExample.class);
        System.out.println(jsonExample);
    }
}
