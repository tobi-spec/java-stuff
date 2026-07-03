package com.example.springprotobuff;

import com.example.springprotobuff.proto.PersonProto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @GetMapping(produces = "application/x-protobuf")
    public PersonProto.Person getPerson() {
        var person = PersonProto.Person.newBuilder()
                .setId(1)
                .setName("Alice")
                .setEmail("alice@example.com")
                .build();
        return person;
    }
}
