package com.example.springhandlefiles.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class GetResourceAsStreamIO {
    /**
     * Java way to read file from resources folder
     */
    public static void main(String[] args) throws IOException {
        try(InputStream inputStream =
                GetResourceAsStreamIO.class.getClassLoader().getResourceAsStream("example.json")) {

            ObjectMapper mapper = new ObjectMapper();
            JsonExample jsonExample = mapper.readValue(inputStream, JsonExample.class);
            System.out.println(jsonExample.toString());
        }
    }
}
