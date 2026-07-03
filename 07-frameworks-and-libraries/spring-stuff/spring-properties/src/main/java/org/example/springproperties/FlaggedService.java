package org.example.springproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FlaggedService {

    @Value("${isActivated}")
    Boolean flag;

    public Boolean getFlag() {
        return flag;
    }
}
