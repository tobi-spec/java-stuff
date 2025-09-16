package com.example.springhandlefiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClassPathResourceIO {
    /**
     * Spring way to read file from resources folder
     */
    public static void main(String[] args) throws IOException {
        Resource resource = new ClassPathResource("example.txt");

        try( InputStream inputStream = resource.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        Resource jsonResource = new ClassPathResource("example.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonExample jsonExample = objectMapper.readValue(jsonResource.getInputStream(), JsonExample.class);
        System.out.println(jsonExample);
    }
}
