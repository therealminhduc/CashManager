package com.epitech.server.http;

import com.epitech.server.payment.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequests {

    URI uri = new URI("http://localhost:8081");
    public HttpRequests() throws URISyntaxException {

    }

    public String postTransaction(PaymentRequest paymentRequest) throws IOException, InterruptedException {
        String requestJson = paymentRequest.toJson();
        HttpRequest httpRequest = HttpRequest.newBuilder(this.uri)
            .POST(HttpRequest.BodyPublishers.ofString(requestJson))
            .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String result = response.body();
        // Besoin de connaitre le format de la réponse
        // Pour déserialiser le json en objet java
        return "ok";
    }
}
