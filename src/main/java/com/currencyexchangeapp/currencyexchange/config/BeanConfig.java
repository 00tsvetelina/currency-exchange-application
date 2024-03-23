package com.currencyexchangeapp.currencyexchange.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class BeanConfig {

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

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

