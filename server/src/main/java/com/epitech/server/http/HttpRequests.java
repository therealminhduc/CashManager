package com.epitech.server.http;

import com.epitech.server.payment.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequests {

    public HttpRequests() throws URISyntaxException {

    }

    public HttpResponse<String> postTransaction(PaymentRequest paymentRequest) throws IOException, InterruptedException, URISyntaxException {
        String requestJson = paymentRequest.toJson();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8081/api/transaction"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestJson))
            .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
