package com.epitech.server.http;

import lombok.Data;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Data
public class HttpRequests {
    URL url = new URL("http://localhost:8081");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();

    public HttpRequests() throws IOException {

    }

    public void setRequestMethodPost() throws ProtocolException {
        con.setRequestMethod("POST");
    }


}
