package com.movie.movieapi.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
    HttpServletRequest 에서 @RequestBody를 받아올 수 있도록 하는 Wrapper class
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private String body;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = getBody(request);
    }

    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8))));
    }

    private String getBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public String getBodyContent() {
        return body;
    }

}
