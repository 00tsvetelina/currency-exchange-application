package com.currencyexchangeapp.currencyexchange.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Configuration
public class HttpClientConfig {

//    @Bean
//    HttpRequest getRequest(String base, String symbol) throws URISyntaxException {
//        return HttpRequest.newBuilder()
//                .uri(new URI("http://data.fixer.io/api/latest?access_key=&base="
//                        + base + "&symbols=" + symbol))
//                .GET()
//                .build();
//    }

    @Bean
    HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }
}

