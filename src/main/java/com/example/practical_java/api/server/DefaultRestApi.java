package com.example.practical_java.api.server;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class DefaultRestApi {

    @GetMapping("/welcome")
    public String welcome(){
        System.out.println(StringUtils.join("Hello ", "this is ", "Spring Boot ", "Rest API"));
        return "Welcome to Spring Boot";
    }

    @GetMapping("/time")
    public String time(){
        return LocalDateTime.now().toString();
    }

}
