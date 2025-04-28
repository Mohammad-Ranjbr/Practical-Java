package com.example.practical.api.server;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @GetMapping("/header-one")
    public String headerByAnnotation(@RequestHeader("User-agent") String headerUserAgent, @RequestHeader(value = "Practical-java" , required = false) String headerPracticalJava) {
        return "User-agent : " + headerUserAgent + " Practical-Java : " + headerPracticalJava;
    }

    @GetMapping("/header-two")
    public String headerByRequest(ServerHttpRequest serverHttpRequest) {
        return "User-agent : " + serverHttpRequest.getHeaders().getFirst("User-agent")
                + " , Practical-Java : " + serverHttpRequest.getHeaders().getValuesAsList("Practical-java");
    }

}
