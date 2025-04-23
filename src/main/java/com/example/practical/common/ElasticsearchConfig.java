package com.example.practical.common;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    // ElasticsearchClient
    // This is the official Java client provided by Elasticsearch and communicates with Elasticsearch using the Java API.
    // With this client, you can do things like:
    // Store and retrieve documents
    // Create and drop indexes
    // Search with Query DSL
    // Work with mappings and analyzers, etc.
    // without having to manually create HTTP requests.
    // This client has a higher abstraction and is mostly used for applications that have tight coupling with Elastic.

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClient restClient = RestClient.builder(
                new HttpHost("192.168.8.226", 9200)).build();

        // Now we define a Transport layer that is responsible for communication between the REST client and the Elasticsearch Java Client.
        // JacksonJsonpMapper() is a mapper that converts JSON data to Java objects and vice versa.
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }

}
