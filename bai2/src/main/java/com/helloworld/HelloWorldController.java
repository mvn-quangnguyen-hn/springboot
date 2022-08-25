package com.helloworld;

import com.helloworld.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/greeting")
    public User printHello(@RequestParam(defaultValue = "World") String name) {
        return new User(1, String.format("Hello, %s!", name));
    }
}
