package com.example.springhandlefiles;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetResourceAsStreamIO {
    /**
     * Java way to read file from resources folder
     */
    public static void main(String[] args) throws IOException {
        try(InputStream inputStream =
                    GetResourceAsStreamIO.class.
                            getClassLoader()
                            .getResourceAsStream("example.txt")){

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        try(InputStream inputStream =
                GetResourceAsStreamIO.class.getClassLoader().getResourceAsStream("example.json")) {

            ObjectMapper mapper = new ObjectMapper();
            JsonExample jsonExample = mapper.readValue(inputStream, JsonExample.class);
            System.out.println(jsonExample.toString());
        }
    }
}
