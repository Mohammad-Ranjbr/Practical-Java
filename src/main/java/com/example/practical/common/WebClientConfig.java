package com.example.practical.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // WebClient
    // This is a generic HTTP client that can connect to any REST API.
    // In this project, WebClient is used to:
    // Delete the index with DELETE /index-name when the application starts.
    // Why? Because ElasticsearchClient (in the versions used by most tutorials) does not have a direct method to completely delete an index, or it is more complicated to use.
    // While with WebClient you can easily send a simple HTTP request

    @Bean(name = "webClientElasticsearch")
    public WebClient webClientElasticsearch() {
        return WebClient.builder()
                .baseUrl("http://192.168.8.226:9200")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
