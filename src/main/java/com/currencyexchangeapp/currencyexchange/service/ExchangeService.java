package com.currencyexchangeapp.currencyexchange.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;


@Service
@AllArgsConstructor
@Slf4j
public class ExchangeService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public Double getExchangeRate(String base, String symbol) {
        try {
            HttpRequest httpRequest = buildExchangeRateRequest(base, symbol);

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Map<String, Object> rateMap = objectMapper.readValue(response.body(), new TypeReference<>() {});
            Map<String, Double> currencyMap = (Map<String, Double>)rateMap.get("data");

            return currencyMap.get(symbol);
        } catch (IOException | InterruptedException ex) {
            log.error("Could not get exchange rate", ex);
        }

        return null;
    }

    private HttpRequest buildExchangeRateRequest(String base, String symbol) {
        try {
            URIBuilder builder = new URIBuilder("https://api.freecurrencyapi.com/v1/latest");
            builder.addParameter("apikey", System.getenv("API_KEY"));
            builder.addParameter("base_currency", base);
            builder.addParameter("currencies", symbol);

            return HttpRequest.newBuilder()
                    .uri(builder.build())
                    .GET()
                    .build();
        } catch (URISyntaxException ex) {
            log.error("Could not build URI", ex);
        }

        return null;
    }
}
