package com.example.springhandlefiles.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class InputStreamIO {
    /**
     * Nativ Java way to read file from any disk location
     */
    public static void main(String[] args) throws IOException {
        try(FileInputStream inputStream = new FileInputStream("spring-stuff/spring-handle-files/src/main/resources/example.json")) {
            ObjectMapper mapper = new ObjectMapper();
            JsonExample jsonExample = mapper.readValue(inputStream, JsonExample.class);
            System.out.println(jsonExample.toString());
        }
    }
}
