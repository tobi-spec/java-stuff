package org.example.springqualifier;

import org.springframework.stereotype.Component;

@Component("barFormatter")
public class BarFormatter implements Formatter{

    @Override
    public String format() {
        return "Bar Format";
    }
}
