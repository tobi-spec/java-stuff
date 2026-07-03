package com.example.springhandlefiles.text;

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
    }
}
