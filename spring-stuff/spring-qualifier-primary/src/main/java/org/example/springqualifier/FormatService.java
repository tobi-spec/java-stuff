package org.example.springqualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FormatService {

    public Formatter formatter;

    @Autowired
    public FormatService(@Qualifier("fooFormatter") Formatter formatter) {
        this.formatter = formatter;
    }

    public void logFormat() {
        System.out.println(formatter.format());
    }
}
