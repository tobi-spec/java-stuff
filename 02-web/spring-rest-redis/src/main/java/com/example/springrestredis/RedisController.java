package com.example.springrestredis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam String key, @RequestParam String value) {
        redisService.save(key, value);
        return new ResponseEntity<>("Saved", HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<String> read(@RequestParam String key) {
        String value = redisService.read(key);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}
