package com.modyo.pokedex.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

import static com.modyo.pokedex.util.Constants.USER_AGENT_KEY;
import static com.modyo.pokedex.util.Constants.USER_AGENT_VALUE;

@Component
public class RestTemplateHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateHelper.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public RestTemplateHelper(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public <T> T getForEntity(String url, Class<T> clazz) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(USER_AGENT_KEY, USER_AGENT_VALUE);
            HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);

            return readValue(response, javaType);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                LOGGER.info("No data found {}", url);
            } else {
                LOGGER.info("rest client exception", exception.getMessage());
            }
        }
        return null;
    }

    private <T> T readValue(ResponseEntity<String> response, JavaType javaType) {
        T result = null;
        if (response.getStatusCode() == HttpStatus.OK ||
                response.getStatusCode() == HttpStatus.CREATED) {
            try {
                result = objectMapper.readValue(response.getBody(), javaType);
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        } else {
            LOGGER.info("No data found {}", response.getStatusCode());
        }
        return result;
    }
}
