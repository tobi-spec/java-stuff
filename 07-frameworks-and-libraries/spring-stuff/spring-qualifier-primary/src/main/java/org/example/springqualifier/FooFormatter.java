package org.example.springqualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component()
@Qualifier("fooFormatter")
public class FooFormatter implements Formatter{

    @Override
    public String format() {
        return "Foo Format";
    }
}
